package com.shashulovskiy.osu.beatmap.model.difficulty;

import lombok.Data;

@Data
public class Difficulty {
    private Long hPDrainRate;
    private Long circleSize;
    private Long overallDifficulty;
    private Long approachRate;
    private Long sliderMultiplier;
    private Long sliderTickRate;
}
