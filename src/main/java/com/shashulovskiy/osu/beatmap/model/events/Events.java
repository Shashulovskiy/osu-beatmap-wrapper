package com.shashulovskiy.osu.beatmap.model.events;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Events {
    private final List<Event> events = new ArrayList<>();

    private void setBackground(Background background) {
        events.add(background);
    }
    private void setBreak(Break breakInstance) {
        events.add(breakInstance);
    }
    private void setVideo(Video video) {
        events.add(video);
    }
}
