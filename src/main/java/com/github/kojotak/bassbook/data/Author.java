package com.github.kojotak.bassbook.data;

import java.util.Collection;
import java.util.List;

public record Author (

        String name,
        Collection<Song> songs

) {

    public Author(String name, Song ... songs){
        this(name, List.of(songs));
    }
}
