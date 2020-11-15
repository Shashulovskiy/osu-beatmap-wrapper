package com.shashulovskiy.osu.beatmap.model.timingpoints;

import com.shashulovskiy.osu.beatmap.model.editor.Bookmarks;
import lombok.Data;

@Data
public class TimingPoint {
    private Integer time;
    private Double beatLength;
    private Integer meter;
    private Integer sampleSet;
    private Integer sampleIndex;
    private Integer volume;
    private Boolean uninherited;
    // TODO Wrapper for effect object
    private Integer effects;

    public static TimingPoint parseTimingPoint(String line) {
        String[] split = line.split(",");
        TimingPoint timingPoint = new TimingPoint();
        timingPoint.setTime(Integer.parseInt(split[0]));
        timingPoint.setBeatLength(Double.parseDouble(split[1]));
        timingPoint.setMeter(Integer.parseInt(split[2]));
        timingPoint.setSampleSet(Integer.parseInt(split[3]));
        timingPoint.setSampleIndex(Integer.parseInt(split[4]));
        timingPoint.setVolume(Integer.parseInt(split[5]));
        timingPoint.setUninherited(Boolean.parseBoolean(split[6]));
        timingPoint.setEffects(Integer.parseInt(split[7]));
        return timingPoint;
    }
}
