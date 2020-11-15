package com.shashulovskiy.osu.beatmap.model.colours;

import lombok.*;

@Data
public class Colour {
    private Integer red;
    private Integer green;
    private Integer blue;

    public static Colour parseColour(String line) {
        String[] split = line.split(",");
        Colour colour = new Colour();
        colour.setRed(Integer.parseInt(split[0]));
        colour.setGreen(Integer.parseInt(split[1]));
        colour.setBlue(Integer.parseInt(split[2]));
        return colour;
    }
}
