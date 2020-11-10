package com.shashulovskiy.osu.beatmap.model.timingpoints;

import lombok.Data;

@Data
public class TimingPoint {
    private Integer time;
    private Long beatLength;
    private Integer meter;
    private Integer sampleSet;
    private Integer sampleIndex;
    private Integer volume;
    private Boolean uninherited;
    // TODO Wrapper for effect object
    private Integer effects;
}
