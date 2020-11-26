package com.shashulovskiy.osu.beatmap.model;

import com.shashulovskiy.osu.beatmap.model.colours.Colours;
import com.shashulovskiy.osu.beatmap.model.difficulty.Difficulty;
import com.shashulovskiy.osu.beatmap.model.editor.Editor;
import com.shashulovskiy.osu.beatmap.model.events.Events;
import com.shashulovskiy.osu.beatmap.model.general.General;
import com.shashulovskiy.osu.beatmap.model.hitobjects.HitObjects;
import com.shashulovskiy.osu.beatmap.model.metadata.Metadata;
import com.shashulovskiy.osu.beatmap.model.timingpoints.TimingPoints ;
import lombok.Data;

@Data
public class Beatmap {
    private final General generalProperties = new General();
    private final Editor editorProperties = new Editor();
    private final Metadata metadataProperties = new Metadata();
    private final Difficulty difficultyProperties = new Difficulty();
    private final Events events = new Events();
    private final TimingPoints timingPoints = new TimingPoints();
    private final Colours colours = new Colours();
    private final HitObjects hitObjects = new HitObjects();
}
