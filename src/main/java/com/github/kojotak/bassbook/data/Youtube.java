package com.github.kojotak.bassbook.data;

import java.util.EnumSet;

public record Youtube(

        Channel channel,
        String id,
        Tuning tuning,
        EnumSet<Technique> technique

) {

    public Youtube(Channel channel, String id) {
        this(channel, id, DEFAULT_TUNING, EnumSet.noneOf(Technique.class));
    }

    public Youtube(Channel channel, String id, EnumSet<Technique> technique) {
        this(channel, id, DEFAULT_TUNING, technique);
    }

    private static final Tuning DEFAULT_TUNING = Tuning.EADG;
}
