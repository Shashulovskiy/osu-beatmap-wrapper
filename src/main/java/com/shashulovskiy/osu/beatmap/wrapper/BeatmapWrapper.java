package com.shashulovskiy.osu.beatmap.wrapper;

import com.shashulovskiy.osu.beatmap.model.Beatmap;
import com.shashulovskiy.osu.beatmap.model.colours.Colour;
import com.shashulovskiy.osu.beatmap.model.colours.Colours;
import com.shashulovskiy.osu.beatmap.model.colours.Combo;
import com.shashulovskiy.osu.beatmap.model.difficulty.Difficulty;
import com.shashulovskiy.osu.beatmap.model.editor.Bookmarks;
import com.shashulovskiy.osu.beatmap.model.editor.Editor;
import com.shashulovskiy.osu.beatmap.model.events.*;
import com.shashulovskiy.osu.beatmap.model.general.General;
import com.shashulovskiy.osu.beatmap.model.hitobjects.*;
import com.shashulovskiy.osu.beatmap.model.metadata.Metadata;
import com.shashulovskiy.osu.beatmap.model.metadata.Tags;
import com.shashulovskiy.osu.beatmap.model.timingpoints.TimingPoint;
import com.shashulovskiy.osu.beatmap.model.timingpoints.TimingPoints;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class BeatmapWrapper {

    Beatmap beatmap = new Beatmap();

    public BeatmapWrapper(final String path) {
        parseFile(Path.of(path));
    }

    private void parseFile(final Path beatmapFile) {
        beatmap = new Beatmap();

        try (final Stream<String> lines = Files.lines(beatmapFile)) {
            parseSections(lines);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void parseSections(final Stream<String> lines) throws IOException {
        final String[] sectionName = {""};
        lines.forEach(line -> {
//            Every section starts with "[SECTION_NAME]"
//            Remember the the current section, which is being parsed and set fields accordingly
            if (line.startsWith("[")) {
                sectionName[0] = line.trim().substring(1, line.length() - 1);
            } else {
                // Comments and empty lines are ignored
                if (!sectionName[0].equals("") && !line.isEmpty() && !line.startsWith("//")) parseSection(sectionName[0], line);
            }
        });
    }

    private void parseSection(final String sectionName, final String line) {
        try {
            switch (sectionName) {
                case "General":
                    parseGeneral(line);
                    break;
                case "Editor":
                    parseEditor(line);
                    break;
                case "Metadata":
                    parseMetadata(line);
                    break;
                case "Difficulty":
                    parseDifficulty(line);
                    break;
                case "Events":
                    parseEvents(line);
                    break;
                case "TimingPoints":
                    parseTimingPoints(line);
                    break;
                case "Colours":
                    parseColours(line);
                    break;
                case "HitObjects":
                    parseHitObjects(line);
                    break;
            }
        } catch (final ReflectiveOperationException e) {
            System.err.printf("Unable to parse \"%s\", at section name %s%n", line, sectionName);
        }
    }

    private void parseGeneral(final String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getGeneralProperties(), General.class);
    }

    private void parseEditor(final String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getEditorProperties(), Editor.class);
    }

    private void parseMetadata(final String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getMetadataProperties(), Metadata.class);
    }

    private void parseDifficulty(final String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getDifficultyProperties(), Difficulty.class);
    }

    private void parseEvents(final String line) throws ReflectiveOperationException {
        // Event type is given by the first number in a comma-separated list
        // Take that out and parse the rest of the list with an according parser
        final int index = line.indexOf(",");
        String key = line.substring(0, index);
        final String value = line.substring(index + 1);

        if (key.equals("0")) key = "Background";
        if (key.equals("1")) key = "Video";
        if (key.equals("2")) key = "Break";

        parseSection(beatmap.getEvents(), Events.class, key, value);
    }

    private void parseTimingPoints(final String line) throws ReflectiveOperationException {
        parseSection(beatmap.getTimingPoints(), TimingPoints.class, "TimingPoint", line);
    }

    private void parseColours(final String line) throws ReflectiveOperationException {
        // Colours section contains either one of the combo colours or
        // SliderTrackOverride/SliderBorder values in key: value format,
        // which have their own parsers
        if (line.startsWith("Combo")) {
            parseSection(beatmap.getColours(), Colours.class, "Combo", line.split(":")[1].trim());
        } else {
            parseKeyValuePair(line, beatmap.getColours(), Colours.class);
        }
    }

    private void parseHitObjects(final String line) throws ReflectiveOperationException {
        // Hit onject type is given by one of the bytes in the type integer value
        // 0 - HitCircle
        // 1 - Slider
        // 3 - Spinner
        // 7 - o!m hold
        final String[] split = line.split(",");
        String key = split[3];

        final int type = Integer.parseInt(key);
        if ((type & (1 << 0)) != 0) {
            key = "HitCircle";
        }

        if ((type & (1 << 1)) != 0) {
            key = "Slider";
        }

        if ((type & (1 << 3)) != 0) {
            key = "Spinner";
        }

        if ((type & (1 << 7)) != 0) {
            key = "Hold";
        }

        parseSection(beatmap.getHitObjects(), HitObjects.class, key, line);
    }

    private <T> void parseKeyValuePair(final String line, final T properties, final Class<T> tClass) throws ReflectiveOperationException {
        final String[] split = line.split(":");
        final String key = split[0].trim();
        final String value = split[1].trim();

        parseSection(properties, tClass, key, value);
    }

    private <T> void parseSection(final T properties, final Class<T> tClass, final String key, final String value) throws ReflectiveOperationException{
        // Get the setter for the field using Reflection and set it to the parsed value
        final String setterName = "set" + key;
        Class<?> parameterType = null;
        Method setter = null;
        for (final Method method : tClass.getDeclaredMethods()) {
            if (method.getName().equals(setterName)) {
                parameterType = method.getParameterTypes()[0];
                setter = method;
                break;
            }
        }

        // If the setter is null, we either have a bug in the parser of the beatmap file has an invalid line
        // TODO: Add custom exceptions
        if (setter == null) {
            throw new ReflectiveOperationException();
        }
        setter.setAccessible(true);
        setter.invoke(properties, parsers.get(parameterType).apply(value));
    }

//    This map contains a parser, that allows you get any beatmap property from a string that represents it
//    in the beatmap file
    private static final Map<Class<?>, Function<String, ?>> parsers = new HashMap<>();

    static {
        parsers.put(String.class, Function.identity());
        parsers.put(Integer.class, Integer::parseInt);
        parsers.put(Boolean.class, Boolean::parseBoolean);
        parsers.put(Double.class, Double::parseDouble);
        parsers.put(Bookmarks.class, Editor::parseBookmarks);
        parsers.put(Long.class, Long::parseLong);
        parsers.put(Tags.class, Metadata::parseTags);
        parsers.put(Background.class, Background::parseBackground);
        parsers.put(Break.class, Break::parseBreak);
        parsers.put(Video.class, Video::parseVideo);
        parsers.put(TimingPoint.class, TimingPoint::parseTimingPoint);
        parsers.put(Combo.class, Combo::parseCombo);
        parsers.put(Colour.class, Colour::parseColour);
        parsers.put(HitCircle.class, HitCircle::parseHitCircle);
        parsers.put(Slider.class, Slider::parseSlider);
        parsers.put(Spinner.class, Spinner::parseSpinner);
        parsers.put(Hold.class, Hold::parseHold);
    }
}