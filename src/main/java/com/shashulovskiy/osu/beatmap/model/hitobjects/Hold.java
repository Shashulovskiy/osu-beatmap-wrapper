package com.shashulovskiy.osu.beatmap.model.hitobjects;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Hold extends HitObject {
    private Integer endTime;

    public static Hold parseHold(String line) {
        String[] split = line.split(",");
        Integer x = Integer.parseInt(split[0]);
        Integer y = Integer.parseInt(split[1]);
        Integer time = Integer.parseInt(split[2]);
        Integer type = Integer.parseInt(split[3]);
        Integer hitSound = Integer.parseInt(split[4]);
        Integer endTime = Integer.parseInt(split[5]);
        List<Integer> hitSample = Arrays.stream(split[6].split(":")).map(Integer::parseInt).collect(Collectors.toList());

        Hold hold = new Hold();
        hold.setX(x);
        hold.setY(y);
        hold.setTime(time);
        hold.setType(type);
        hold.setHitSound(hitSound);
        hold.setEndTime(endTime);
        hold.setHitSample(hitSample);

        return hold;
    }
}
