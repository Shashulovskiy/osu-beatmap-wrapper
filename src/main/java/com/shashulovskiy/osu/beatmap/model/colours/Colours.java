package com.shashulovskiy.osu.beatmap.model.colours;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Colours {
    private List<Combo> combos = new ArrayList<>();
    private Colour sliderTrackOverride;
    private Colour sliderBorder;

    private void setCombo(String line) {
        combos.add(Combo.parseCombo(line));
    }
}
