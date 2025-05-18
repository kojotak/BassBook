package com.github.kojotak.bassbook.data;

import java.util.Comparator;

public interface Named {
    String getName();

    Comparator<Named> BY_NAME = Comparator.comparing(Named::getName);
}
