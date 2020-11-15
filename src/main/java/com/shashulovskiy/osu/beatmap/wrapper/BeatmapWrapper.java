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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BeatmapWrapper {

    Beatmap beatmap = new Beatmap();

    public BeatmapWrapper(String path) {
        parseFile(new File(path));
    }

    private void parseFile(File beatmapFile) {
        beatmap = new Beatmap();

        try (BufferedReader br = new BufferedReader(new FileReader(beatmapFile))) {
            parseSections(br);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseSections(BufferedReader br) throws IOException {
        String line;
        String sectionName = "";
        while ((line = br.readLine()) != null) {
            if (line.startsWith("[")) {
                sectionName = line.trim().substring(1, line.length() - 1);
            } else {
                if (!sectionName.equals("") && !line.isEmpty() && !line.startsWith("//")) parseSection(sectionName, line);
            }
        }
    }

    private void parseSection(String sectionName, String line) {
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
        } catch (ReflectiveOperationException e) {
            System.err.printf("Unable to parse \"%s\", at section name %s%n", line, sectionName);
        }
    }

    private void parseGeneral(String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getGeneralProperties(), General.class);
    }

    private void parseEditor(String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getEditorProperties(), Editor.class);
    }

    private void parseMetadata(String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getMetadataProperties(), Metadata.class);
    }

    private void parseDifficulty(String line) throws ReflectiveOperationException {
        parseKeyValuePair(line, beatmap.getDifficultyProperties(), Difficulty.class);
    }

    private void parseEvents(String line) throws ReflectiveOperationException {
        int index = line.indexOf(",");
        String key = line.substring(0, index);
        String value = line.substring(index + 1);

        if (key.equals("0")) key = "Background";
        if (key.equals("1")) key = "Video";
        if (key.equals("2")) key = "Break";

        parseSection(beatmap.getEvents(), Events.class, key, value);
    }

    private void parseTimingPoints(String line) throws ReflectiveOperationException {
        parseSection(beatmap.getTimingPoints(), TimingPoints.class, "TimingPoint", line);
    }

    private void parseColours(String line) throws ReflectiveOperationException {
        if (line.startsWith("Combo")) {
            parseSection(beatmap.getColours(), Colours.class, "Combo", line.split(":")[1].trim());
        } else {
            parseKeyValuePair(line, beatmap.getColours(), Colours.class);
        }
    }

    private void parseHitObjects(String line) throws ReflectiveOperationException {
        String[] split = line.split(",");
        String key = split[3];

        int type = Integer.parseInt(key);
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

    private <T> void parseKeyValuePair(String line, T properties, Class<T> tClass) throws ReflectiveOperationException {
        String[] split = line.split(":");
        String key = split[0].trim();
        String value = split[1].trim();

        parseSection(properties, tClass, key, value);
    }

    private <T> void parseSection(T properties, Class<T> tClass, String key, String value) throws ReflectiveOperationException{
        String setterName = "set" + key;
        Class<?> parameterType = null;
        Method setter = null;
        for (Method method : tClass.getDeclaredMethods()) {
            if (method.getName().equals(setterName)) {
                parameterType = method.getParameterTypes()[0];
                setter = method;
                break;
            }
        }

        if (setter == null) {
            throw new ReflectiveOperationException();
        }
        setter.setAccessible(true);
        setter.invoke(properties, parsers.get(parameterType).apply(value));
    }

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