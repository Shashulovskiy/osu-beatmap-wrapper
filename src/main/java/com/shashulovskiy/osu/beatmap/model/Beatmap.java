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
    private General generalProperties;
    private Editor editorProperties;
    private Metadata metadataProperties;
    private Difficulty difficultyProperties;
    private Events events;
    private TimingPoints timingPoints;
    private Colours colours;
    private HitObjects hitObjects;
}
