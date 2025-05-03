package com.github.kojotak.bassbook.data;

import java.util.Collection;
import java.util.List;

public record Song(

        String name,
        Meter meter,
        Feel feel,
        Collection<Youtube> plays

) {

    public Song(String name, Youtube play){
        this(name, DEFAULT_METER, DEFAULT_FEEL, List.of(play));
    }

    public Song(String name, Collection<Youtube> plays){
        this(name, DEFAULT_METER, DEFAULT_FEEL, plays);
    }

    public Song(String name, Meter meter, Youtube play){
        this(name, meter, DEFAULT_FEEL, List.of(play));
    }

    public Song(String name, Meter meter, Collection<Youtube> plays){
        this(name, meter, DEFAULT_FEEL, plays);
    }

    public Song(String name, Feel feel, Youtube play){
        this(name, DEFAULT_METER, feel, List.of(play));
    }

    public Song(String name, Feel feel, Collection<Youtube> plays){
        this(name, DEFAULT_METER, feel, plays);
    }

    private static final Meter DEFAULT_METER = Meter.COMMON;
    private static final Feel DEFAULT_FEEL = Feel.STRAIGHT;

}
