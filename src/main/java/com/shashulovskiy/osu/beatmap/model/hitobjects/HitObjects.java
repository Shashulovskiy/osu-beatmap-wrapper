package com.shashulovskiy.osu.beatmap.model.hitobjects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HitObjects {
    private List<HitObject> hitObjects = new ArrayList<>();

    private void setHitCircle(HitCircle hitCircle) { hitObjects.add(hitCircle); }
    private void setSlider(Slider slider) { hitObjects.add(slider); }
    private void setSpinner(Spinner spinner) { hitObjects.add(spinner); }
    private void setHold(Hold hold) { hitObjects.add(hold); }
}
