package com.shashulovskiy.osu.beatmap.model.general;

import lombok.Data;

@Data
public class General {
    private String audioFilename;
    private Integer audioLeadIn;
    private String audioHash;
    private Integer previewTime;
    private Integer countdown;
    private String sampleSet;
    private Double stackLeniency;
    private Integer mode;
    private Boolean letterboxInBreaks;
    private Boolean storyFireInFront;
    private Boolean useSkinSprites;
    private Boolean alwaysShowPlayfield;
    private String overlayPosition;
    private String skinPreference;
    private Boolean epilepsyWarning;
    private Integer countdownOffset;
    private Boolean specialStyle;
    private Boolean widescreenStoryboard;
    private Boolean sampleMatchPlaybackRate;
}
