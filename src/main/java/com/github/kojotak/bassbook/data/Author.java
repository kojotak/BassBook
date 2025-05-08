package com.github.kojotak.bassbook.data;

public enum Author implements Named  {

    BILLY_IDOL("Billy Idol"),
    MUSE("Muse"),
    NINA_SIMONE("Nina Simone"),
    PHIL_COLLINS("Phil Collins"),
    RAY_CHARLES("Ray Charles"),
    RAMMSTEIN("Rammstein"),
    REM("R.E.M"),
    RHCP("Red hot chilli peppers"),
    SIA("Sia"),
    TALKING_HEADS("Talking heads"),
    THE_BEATLES("The Beatles"),
    THE_BLACK_EYED_PEAS("The Black eyed peas"),
    THE_CRANBERRIES("The Cranberries"),
    THE_PRODIGY("The Prodigy"),
    THE_WHITE_STRIPES("The White stripes"),
    U2("U2")
    ;

    Author(String name){
        this.name = name;
    }
    private final String name;

    @Override
    public String getName() {
        return name;
    }
}
