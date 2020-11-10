package com.shashulovskiy.osu.beatmap.model.events;

import lombok.Data;

@Data
public class Background implements Event {
    private String filename;
    private Integer xOffset;
    private Integer yOffset;
}
