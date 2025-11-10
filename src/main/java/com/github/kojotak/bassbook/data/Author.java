package com.github.kojotak.bassbook.data;

public enum Author implements Named  {

    ACDC("AC/DC"),
    AEROSMITH("Aerosmith"),
    ADELE("Adele"),
    AL_GREEN("Al Green"),
    ARCTIC_MONKEYS("Arctic Monkeys"),
    ARETHA_FRANKLIN("Aretha Franklin"),
    BILLY_IDOL("Billy Idol"),
    BEN_E_KING("Ben E. King"),
    BONEY_M("Boney M"),
    BON_JOVI("Bon Jovi"),
    BRITNEY_SPEARS("Britney Spears"),
    BRUNO_MARS("Bruno Mars"),
    CHRIS_ISAAK("Chris Isaak"),
    COLDPLAY("Coldplay"),
    DEEP_PURPLE("Deep Purple"),
    DEFTONES("Deftones"),
    DOORS("Doors"),
    DUA_LIPA("Dua Lipa"),
    EARTH_WIND_FIRE("Earth, Wind & Fire"),
    FALL_OUT_BOY("Fall Out Boy"),
    FLEETWOOD_MAC("Fleetwood Mac"),
    FOO_FIGHTERS("Foo Fighters"),
    GORILLAZ("Gorillaz"),
    GREEN_DAY("Green Day"),
    GUANO_APES("Guano Apes"),
    JAMIROQUAI("Jamiroquai"),
    JEFFERSON_AIRPLANE("Jefferson Airplane"),
    JOURNEY("Journey"),
    LABELLE("LaBelle"),
    LED_ZEPPELIN("Led Zeppelin"),
    LENNY_KRAVITZ("Lenny Kravitz"),
    LINKIN_PARK("Linkin Park"),
    MC_HAMMER("MC Hammer"),
    METALLICA("Metallica"),
    MICHAEL_JACKSON("Michael Jackson"),
    MUSE("Muse"),
    NIGHTWISH("Nightwish"),
    NINA_SIMONE("Nina Simone"),
    ONE_REPUBLIC("One Republic"),
    PEARL_JAM("Pearl Jam"),
    PHARELL_WILLIAMS("Pharell Williams"),
    PHIL_COLLINS("Phil Collins"),
    PINK("P!nk"),
    PINK_FLOYD("Pink Floyd"),
    POLICE("Police"),
    PORCUPINE_TREE("Porcupine Tree"),
    PUSCIFER("Puscifer"),
    QUEEN("Queen"),
    RADIOHEAD("Radiohead"),
    RAY_CHARLES("Ray Charles"),
    RAMMSTEIN("Rammstein"),
    RATM("Rage against the machine"),
    REM("R.E.M"),
    RHCP("Red hot chilli peppers"),
    SADE("Sade"),
    SIA("Sia"),
    SOAD("System of a Down"),
    SHERYL_CROW("Sheryl Crow"),
    STING("Sting"),
    SURVIVOR("Survivor"),
    TALKING_HEADS("Talking heads"),
    TOTO("Toto"),
    THE_BEATLES("The Beatles"),
    THE_BLACK_EYED_PEAS("The Black eyed peas"),
    THE_CRANBERRIES("The Cranberries"),
    THE_PRODIGY("The Prodigy"),
    THE_WHITE_STRIPES("The White stripes"),
    TRACY_CHAPMAN("Tracy Chapman"),
    U2("U2"),
    WITHIN_TEMPTATION("Within Temptation")
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
