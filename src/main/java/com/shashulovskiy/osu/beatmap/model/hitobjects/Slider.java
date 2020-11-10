package com.shashulovskiy.osu.beatmap.model.hitobjects;

import lombok.Data;

import java.util.List;

@Data
public class Slider extends HitObject {
    private Character curveType;
    private List<CurvePoint> curvePoints;
    private Integer slides;
    private Long length;
    private List<Integer> edgeSounds;
    private List<String> edgeSets;
}
