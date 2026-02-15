package com.github.kojotak.bassbook.data;

import java.util.List;

public record Author (

    String name,
    List<Song> songs

) implements Named {

    @Override
    public String getName() {
        return name;
    }
}
