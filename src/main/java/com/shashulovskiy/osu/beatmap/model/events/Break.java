package com.shashulovskiy.osu.beatmap.model.events;

import lombok.Data;

@Data
public class Break implements Event {
    private Integer startTime;
    private Integer endTime;
}
