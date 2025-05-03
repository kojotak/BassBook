package com.github.kojotak.bassbook.data;

public record Meter(

        int count,
        int perBeat

) {

    public static final Meter COMMON = new Meter(4, 4);
    public static final Meter CUT_TIME = new Meter(2, 2);

}
