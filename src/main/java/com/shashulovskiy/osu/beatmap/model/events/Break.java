package com.shashulovskiy.osu.beatmap.model.events;

import lombok.Data;

@Data
public class Break implements Event {
    private Integer startTime;
    private Integer endTime;

    public static Break parseBreak(String line) {
        String[] split = line.split(",");
        Break breakInstance = new Break();
        breakInstance.setStartTime(Integer.parseInt(split[0]));
        breakInstance.setEndTime(Integer.parseInt(split[1]));
        return breakInstance;
    }
}
