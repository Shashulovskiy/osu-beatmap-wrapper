package com.shashulovskiy.osu.beatmap.model.metadata;

import lombok.Data;

import java.util.List;

@Data
public class Metadata {
    private String title;
    private String titleUnicode;
    private String artist;
    private String artistUnicode;
    private String creator;
    private String version;
    private String source;
    private List<String> tags;
    private Integer beatmapID;
    private Integer beatmapSetID;
}
