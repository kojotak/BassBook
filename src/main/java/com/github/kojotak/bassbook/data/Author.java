package com.github.kojotak.bassbook.data;

public enum Author implements Named  {

    ACDC("AC/DC"),
    ADELE("Adele"),
    AL_GREEN("Al Green"),
    BILLY_IDOL("Billy Idol"),
    BON_JOVI("Bon Jovi"),
    COLDPLAY("Coldplay"),
    DOORS("Doors"),
    DUA_LIPA("Dua Lipa"),
    EARTH_WIND_FIRE("Earth, Wind & Fire"),
    FLEETWOOD_MAC("Fleetwood Mac"),
    FOO_FIGHTERS("Foo Fighters"),
    GORILLAZ("Gorillaz"),
    GREEN_DAY("Green Day"),
    GUANO_APES("Guano Apes"),
    JEFFERSON_AIRPLANE("Jefferson Airplane"),
    JOURNEY("Journey"),
    LABELLE("LaBelle"),
    LED_ZEPPELIN("Led Zeppelin"),
    LENNY_KRAVITZ("Lenny Kravitz"),
    LINKIN_PARK("Linkin Park"),
    METALLICA("Metallica"),
    MICHAEL_JACKSON("Michael Jackson"),
    MUSE("Muse"),
    NIGHTWISH("Nightwish"),
    NINA_SIMONE("Nina Simone"),
    ONE_REPUBLIC("One Republic"),
    PEARL_JAM("Pearl Jam"),
    PHARELL_WILLIAMS("Pharell Williams"),
    PHIL_COLLINS("Phil Collins"),
    PINK_FLOYD("Pink Floyd"),
    QUEEN("Queen"),
    RAY_CHARLES("Ray Charles"),
    RAMMSTEIN("Rammstein"),
    RATM("Rage against the machine"),
    REM("R.E.M"),
    RHCP("Red hot chilli peppers"),
    SIA("Sia"),
    SOAD("System of a Down"),
    SHERYL_CROW("Sheryl Crow"),
    TALKING_HEADS("Talking heads"),
    TOTO("Toto"),
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
