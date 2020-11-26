package com.shashulovskiy.osu.beatmap.model.events;

import lombok.Data;

@Data
public class Video implements Event {
    private Integer startTime;
    private String filename;
    private Integer xOffset;
    private Integer yOffset;

    public static Video parseVideo(String line) {
        String[] split = line.split(",");
        Video video = new Video();
        video.setStartTime(Integer.parseInt(split[0]));
        video.setFilename(split[1]);
        video.setXOffset(Integer.parseInt(split[2]));
        video.setYOffset(Integer.parseInt(split[3]));
        return video;
    }
}
