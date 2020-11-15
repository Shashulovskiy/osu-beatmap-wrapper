package com.shashulovskiy.osu.beatmap.model.timingpoints;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TimingPoints {
    private List<TimingPoint> timingPoints = new ArrayList<>();

    private void setTimingPoint(TimingPoint timingPoint) {
        timingPoints.add(timingPoint);
    }
}
