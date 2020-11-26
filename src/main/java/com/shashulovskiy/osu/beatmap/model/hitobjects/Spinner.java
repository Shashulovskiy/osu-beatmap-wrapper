package com.shashulovskiy.osu.beatmap.model.hitobjects;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Spinner extends HitObject {
    private Integer endTime;

    public static Spinner parseSpinner(String line) {
        String[] split = line.split(",");
        Integer x = Integer.parseInt(split[0]);
        Integer y = Integer.parseInt(split[1]);
        Integer time = Integer.parseInt(split[2]);
        Integer type = Integer.parseInt(split[3]);
        Integer hitSound = Integer.parseInt(split[4]);
        Integer endTime = Integer.parseInt(split[5]);
        List<Integer> hitSample = Arrays.stream(split[6].split(":")).map(Integer::parseInt).collect(Collectors.toList());

        Spinner spinner = new Spinner();
        spinner.setX(x);
        spinner.setY(y);
        spinner.setTime(time);
        spinner.setType(type);
        spinner.setHitSound(hitSound);
        spinner.setEndTime(endTime);
        spinner.setHitSample(hitSample);

        return spinner;
    }
}
