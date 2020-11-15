package com.shashulovskiy.osu.beatmap.model.hitobjects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HitCircle extends HitObject{

    public HitCircle(Integer x, Integer y, Integer time, Integer type, Integer hitSound, List<Integer> hitSample) {
        setX(x);
        setY(y);
        setTime(time);
        setType(type);
        setHitSound(hitSound);
        setHitSample(hitSample);
    }

    public static HitCircle parseHitCircle(String line) {
        String[] split = line.split(",");
        Integer x = Integer.parseInt(split[0]);
        Integer y = Integer.parseInt(split[1]);
        Integer time = Integer.parseInt(split[2]);
        Integer type = Integer.parseInt(split[3]);
        Integer hitSound = Integer.parseInt(split[4]);
        List<Integer> hitSample = Arrays.stream(split[5].split(":")).map(Integer::parseInt).collect(Collectors.toList());

        return new HitCircle(x, y, time, type, hitSound, hitSample);
    }
}
