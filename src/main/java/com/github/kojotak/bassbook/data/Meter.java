package com.github.kojotak.bassbook.data;

import java.util.Optional;

public record Meter (

        int count,
        int perBeat

) implements Comparable<Meter> {

    public static final Meter COMMON = new Meter(4, 4);
    public static final Meter CUT_TIME = new Meter(2, 2);

    @Override
    public int compareTo(Meter otherNullable) {
        var other = Optional.ofNullable(otherNullable).orElse(COMMON);
        var compareBeats = Integer.compare(this.perBeat, other.perBeat);
        var compareCount = Integer.compare(this.count, other.count);
        return compareBeats == 0 ? compareCount : compareBeats;
    }
}
