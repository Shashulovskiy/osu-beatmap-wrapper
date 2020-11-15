package com.shashulovskiy.osu.beatmap.model.metadata;

import com.shashulovskiy.osu.beatmap.model.editor.Bookmarks;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Metadata {
    private String title;
    private String titleUnicode;
    private String artist;
    private String artistUnicode;
    private String creator;
    private String version;
    private String source;
    private Tags tags;
    private Integer beatmapID;
    private Integer beatmapSetID;

    public List<String> getTags() {
        return tags.getTags();
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public static Tags parseTags(String s) {
        return new Tags(Arrays.stream(s.split(" ")).map(String::trim).collect(Collectors.toList()));
    }
}
