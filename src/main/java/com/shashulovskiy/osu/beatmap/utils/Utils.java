package com.shashulovskiy.osu.beatmap.utils;

public class Utils {
    public static String toCamelCase(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }
}
