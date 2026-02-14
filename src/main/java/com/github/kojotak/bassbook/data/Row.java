package com.github.kojotak.bassbook.data;

import org.springframework.lang.Nullable;

public record Row (

        Author author,
        Song song,
        @Nullable Tag tag

) {
}
