package com.shashulovskiy.osu.beatmap.model.hitobjects;

import lombok.Data;

@Data
public class CurvePoint {
    private Integer x;
    private Integer y;

    public static CurvePoint parseCurvePoint(String line) {
        String[] split = line.split(":");
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setX(Integer.parseInt(split[0]));
        curvePoint.setY(Integer.parseInt(split[1]));
        return curvePoint;
    }
}
