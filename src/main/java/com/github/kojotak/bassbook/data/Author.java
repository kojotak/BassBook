package com.github.kojotak.bassbook.data;

import java.util.List;

public record Author (

    String name,
    List<Song> songs

) {

    public Author(AuthorEnum name, List<Song> songs) {
        this(name.getName(), songs);
    }

}
