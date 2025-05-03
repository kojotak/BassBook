package com.github.kojotak.bassbook.data;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public record Song(

        String name,
        Meter meter,
        Feel feel,
        Collection<Youtube> plays

) {

    public Song(String name, Meter meter, Feel feel, Youtube... plays) {
        this(name, meter, feel, Stream.of(plays).sorted(Comparator.comparing( p -> p.channel().label)).toList());
    }

    public Song(String name, Youtube play) {
        this(name, DEFAULT_METER, DEFAULT_FEEL, play);
    }

    public Song(String name, Youtube... plays) {
        this(name, DEFAULT_METER, DEFAULT_FEEL, plays);
    }

    public Song(String name, Meter meter, Youtube play) {
        this(name, meter, DEFAULT_FEEL, play);
    }

    public Song(String name, Meter meter, Youtube... plays) {
        this(name, meter, DEFAULT_FEEL, plays);
    }

    public Song(String name, Feel feel, Youtube play) {
        this(name, DEFAULT_METER, feel, play);
    }

    public Song(String name, Feel feel, Youtube... plays) {
        this(name, DEFAULT_METER, feel, plays);
    }

    private static final Meter DEFAULT_METER = Meter.COMMON;
    private static final Feel DEFAULT_FEEL = Feel.STRAIGHT;

}
