package com.shashulovskiy.osu.beatmap.model.editor;

import lombok.*;

import java.util.List;

@Data
public class Bookmarks {
    List<Integer> bookmarks;

    public Bookmarks(List<Integer> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
