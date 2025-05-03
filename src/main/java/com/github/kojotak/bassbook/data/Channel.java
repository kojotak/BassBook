package com.github.kojotak.bassbook.data;

public enum Channel {

    BRAND73("Brand73","Brand73"),
    CARLOS_CARLESI("Carlos Carlesi", "carlucho1012"),
    COVERSOLUTIONS("CoverSolutions","CoverSolutions"),
    GEORDIE_BASSIST("The Geordie Bassist", "TheGeordieBassist"),
    LEO("LeoBassCovers", "LeoBassCovers2"),
    NATE_NAVARRO("Nate Navarro", "NateNavarro"),
    NOCCO_CAT("nocco cat", "noccobass"),
    TOM_BORNEMANN("Tom Bornemann", "basscination"),
    ROBERTO_YANGUS("Roberto Yanguas", "robertoyanguas8990")
    ;

    public final String name;

    /**
     * Channel id
     */
    public final String id;

    Channel(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
