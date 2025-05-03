package com.github.kojotak.bassbook.data;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public record Author (

        String name,
        Collection<Song> songs

) {

    public Author(String name, Song ... songs){
        this(name, Stream.of(songs).sorted(Comparator.comparing(Song::name)).toList());
    }
}
