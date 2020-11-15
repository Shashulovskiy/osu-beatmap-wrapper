package com.shashulovskiy.osu.beatmap.model.metadata;

import lombok.Data;

import java.util.List;

@Data
public class Tags {
    List<String> tags;

    public Tags(List<String> tags) {
        this.tags = tags;
    }
}
