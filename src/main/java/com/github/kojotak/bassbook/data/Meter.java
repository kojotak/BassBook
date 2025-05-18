package com.github.kojotak.bassbook.data;

public record Meter (

        int count,
        int perBeat

) implements Comparable<Meter> {

    public static final Meter COMMON = new Meter(4, 4);
    public static final Meter CUT_TIME = new Meter(2, 2);

    @Override
    public int compareTo(Meter other) {
        var compareBeats = Integer.compare(this.perBeat, other.perBeat);
        var compareCount = Integer.compare(this.count, other.count);
        return compareBeats == 0 ? compareCount : compareBeats;
    }

    public String toLabel(){
        return count + "_" + perBeat;
    }
}
