package com.shashulovskiy.osu.beatmap.model.events;

import com.shashulovskiy.osu.beatmap.model.Beatmap;
import lombok.Data;

@Data
public class Background implements Event {
    private String filename;
    private Integer xOffset;
    private Integer yOffset;

    public static Background parseBackground(String line) {
        String[] split = line.split(",");
        Background background = new Background();
        background.setFilename(split[1]);
        background.setXOffset(Integer.parseInt(split[2]));
        background.setYOffset(Integer.parseInt(split[3]));
        return background;
    }
}
