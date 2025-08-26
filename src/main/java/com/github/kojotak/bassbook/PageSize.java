package com.github.kojotak.bassbook;

public enum PageSize {
    TEN(10),
    TWENTY(20),
    FIFTY(50);

    private final int size;

    PageSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
