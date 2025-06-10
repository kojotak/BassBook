package com.github.kojotak.bassbook.data;

public enum Channel {

    ANDREA_BERTIX("Andrea Bertix", "AndreaBertix"),
    BRAND73("Brand73","Brand73"),
    CARLOS_CARLESI("Carlos Carlesi", "carlucho1012"),
    COVERSOLUTIONS("CoverSolutions","CoverSolutions"),
    EUBASS("EuBasss", "eubasss"),
    FLORIAN_BASSO("Florian Basso", "florianbasso"),
    GEORDIE_BASSIST("The Geordie Bassist", "TheGeordieBassist"),
    HARRY("Harry - Music & Stuff", "HarryMusicStuff"),
    LEO("LeoBassCovers", "LeoBassCovers2"),
    LOVE_PEACE_BASS("Love, Peace, Bass", "LovePeaceBass"),
    NATE_NAVARRO("Nate Navarro", "NateNavarro"),
    PIANOPRINTER("PianoPrinter","pianoprinterstudio"),
    NICKZ911("NickZ911", "NickZ911"),
    NOCCO_CAT("nocco cat", "noccobass"),
    TOM_BORNEMANN("Tom Bornemann", "basscination"),
    ROBERTO_YANGUS("Roberto Yanguas", "robertoyanguas8990"),
    VX_MATTHEW("VX Matthew", "vxmatthew7270"),
    YELLOW_TABS("Yellow Tabs", "YellowTabs");

    public final String label;

    /**
     * Channel id
     */
    public final String id;

    Channel(String name, String id) {
        this.label = name;
        this.id = id;
    }
}
