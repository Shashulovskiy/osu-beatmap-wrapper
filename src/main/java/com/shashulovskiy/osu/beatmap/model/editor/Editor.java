package com.shashulovskiy.osu.beatmap.model.editor;

import lombok.Data;

import java.util.List;

@Data
public class Editor {
    private List<Integer> bookmarks;
    private Long distanceSpacing;
    private Long beatDivisor;
    private Integer gridSize;
    private Long timelineZoom;
}
