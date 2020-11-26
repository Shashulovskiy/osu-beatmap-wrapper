package com.shashulovskiy.osu.beatmap.model.editor;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Editor {
    private Bookmarks bookmarks;
    private Double distanceSpacing;
    private Double beatDivisor;
    private Integer gridSize;
    private Double timelineZoom;

    public List<Integer> getBookmarks() {
        return bookmarks.getBookmarks();
    }

    public void setBookmarks(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    public static Bookmarks parseBookmarks(String s) {
        return new Bookmarks(Arrays.stream(s.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList()));
    }
}
