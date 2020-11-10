package com.shashulovskiy.osu.beatmap.model.hitobjects;

import lombok.Data;

import java.util.List;

@Data
public abstract class HitObject {
    private Integer x;
    private Integer y;
    private Integer type;
    private Integer hitSound;
    // TODO properly implement hitSamples
    private List<Integer> hitSample;
}
