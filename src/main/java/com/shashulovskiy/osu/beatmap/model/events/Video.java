package com.shashulovskiy.osu.beatmap.model.events;

import lombok.Data;

@Data
public class Video implements Event {
    private Integer startTime;
    private String filename;
    private Integer xOffset;
    private Integer yOffset;
}
