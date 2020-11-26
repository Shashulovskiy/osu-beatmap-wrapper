package com.shashulovskiy.osu.beatmap.model.difficulty;

import lombok.Data;

@Data
public class Difficulty {
    private Double HPDrainRate;
    private Double circleSize;
    private Double overallDifficulty;
    private Double approachRate;
    private Double sliderMultiplier;
    private Double sliderTickRate;
}
