package com.shashulovskiy.osu.beatmap.model.colours;

import lombok.Data;

@Data
public class Combo {
    private Colour colour;

    public static Combo parseCombo(String line) {
        Combo combo = new Combo();
        combo.setColour(Colour.parseColour(line));
        return combo;
    }
}
