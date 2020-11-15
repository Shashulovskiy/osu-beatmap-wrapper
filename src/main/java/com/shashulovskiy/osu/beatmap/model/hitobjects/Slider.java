package com.shashulovskiy.osu.beatmap.model.hitobjects;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Slider extends HitObject {
    private Character curveType;
    private List<CurvePoint> curvePoints;
    private Integer slides;
    private Double length;
    private List<Integer> edgeSounds;
    private List<String> edgeSets;

    public static Slider parseSlider(String line) {
        String[] split = line.split(",");
        Integer x = Integer.parseInt(split[0]);
        Integer y = Integer.parseInt(split[1]);
        Integer time = Integer.parseInt(split[2]);
        Integer type = Integer.parseInt(split[3]);
        Integer hitSound = Integer.parseInt(split[4]);
        char curveType = split[5].split("\\|")[0].charAt(0);
        List<CurvePoint> curvePoints = new ArrayList<>();
        for (String curvePoint : split[5].split("\\|")) {
            if (curvePoint.charAt(0) != curveType) {
                curvePoints.add(CurvePoint.parseCurvePoint(curvePoint));
            }
        }
        Integer slides = Integer.parseInt(split[6]);
        Double length = Double.parseDouble(split[7]);
        List<Integer> edgeSounds = null;
        if (split.length > 8) {
            edgeSounds = Arrays.stream(split[8].split("\\|")).map(Integer::parseInt).collect(Collectors.toList());
        }
        List<String> edgeSets = null;
        if (split.length > 9) {
            edgeSets = Arrays.stream(split[9].split("\\|")).collect(Collectors.toList());
        }
        List<Integer> hitSample = null;
        if (split.length > 10) {
            hitSample = Arrays.stream(split[10].split(":")).map(Integer::parseInt).collect(Collectors.toList());
        }

        // TODO Refactor with Builder pattern
        Slider slider = new Slider();
        slider.setX(x);
        slider.setY(y);
        slider.setTime(time);
        slider.setType(type);
        slider.setHitSound(hitSound);
        slider.setHitSample(hitSample);
        slider.setCurveType(curveType);
        slider.setCurvePoints(curvePoints);
        slider.setSlides(slides);
        slider.setLength(length);
        slider.setEdgeSounds(edgeSounds);
        slider.setEdgeSets(edgeSets);
        slider.setHitSample(hitSample);

        return slider;
    }
}
