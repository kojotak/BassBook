package com.github.kojotak.bassbook.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import java.util.EnumSet;
import java.util.Optional;

public record Youtube(

        Channel channel,
        String id,
        @Nullable Tuning tuning,
        EnumSet<Technique> technique

) {

    @JsonIgnore
    public Tuning tuningOrDefault(){
        return Optional.ofNullable(tuning).orElse(Tuning.EADG);
    }

    public Youtube(Channel channel, String id) {
        this(channel, id, null, EnumSet.noneOf(Technique.class));
    }

    public Youtube(Channel channel, String id, EnumSet<Technique> technique) {
        this(channel, id, null, technique);
    }

}
