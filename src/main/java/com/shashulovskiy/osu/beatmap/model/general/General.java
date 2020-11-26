package com.shashulovskiy.osu.beatmap.model.general;

import lombok.Data;

@Data
public class General {
    private String audioFilename;
    private Integer audioLeadIn = 0;
    private String audioHash;
    private Integer previewTime = -1;
    private Integer countdown = 1;
    private String sampleSet = "Normal";
    private Double stackLeniency = 0.7;
    private Integer mode = 0;
    private Boolean letterboxInBreaks = false;
    private Boolean storyFireInFront = true;
    private Boolean useSkinSprites = false;
    private Boolean alwaysShowPlayfield = false;
    private String overlayPosition = "NoChange";
    private String skinPreference;
    private Boolean epilepsyWarning = false;
    private Integer countdownOffset = 0;
    private Boolean specialStyle = false;
    private Boolean widescreenStoryboard = false;
    private Boolean sampleMatchPlaybackRate = false;
}
