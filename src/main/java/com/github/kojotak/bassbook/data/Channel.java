package com.github.kojotak.bassbook.data;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Channel {

    ANDRE_CARVALHO("André Carvalho", "adrcarvalho93"),
    ANDREA_BERTIX("Andrea Bertix", "AndreaBertix"),
    BASS_MONKEY("BassMonkey","ytbassmonkey"),
    BASSCOVERS88("Basscovers88","basscovers88"),
    BASSCOVER_PETE("BasscoverPete","basscoverpete"),
    BASS_GUITAR_IQ("BassGuitarIQ","BassGuitarIQ"),
    BRAND73("Brand73","Brand73"),
    CARLOS_CARLESI("Carlos Carlesi", "carlucho1012"),
    COVERSOLUTIONS("CoverSolutions","CoverSolutions"),
    EUBASS("EuBasss", "eubasss"),
    FOTIS_TOUMANIDES("Fotis Toumanides Bass", "fotistoumanidesbass"),
    FLORIAN_BASSO("Florian Basso", "florianbasso"),
    FUSILLI_JERRY("Fusilli Jerry", "FusilliJerryBass"),
    GEORDIE_BASSIST("The Geordie Bassist", "TheGeordieBassist"),
    HARRY("Harry - Music & Stuff", "HarryMusicStuff"),
    HOW_TO_PLAY_BASSLINES("How to play Basslines", "HowtoplayBasslines"),
    KIMME08("김미의 기분좋은일", "Kimme08"),
    LEO("LeoBassCovers", "LeoBassCovers2"),
    LOVE_PEACE_BASS("Love, Peace, Bass", "LovePeaceBass"),
    NATE_NAVARRO("Nate Navarro", "NateNavarro"),
    PIANOPRINTER("PianoPrinter","pianoprinterstudio"),
    NICKZ911("NickZ911", "NickZ911"),
    NOCCO_CAT("nocco cat", "noccobass"),
    SAITO("SAITO【毎日ベースチャンネル】", "saitohironobu3811"),
    SAMBOAT_MUSIC("SamboatMusic - Bass Covers", "SamboatMusicBassCovers"),
    SERGIO_GONCALVS("Sérgio Gonçâlvs Bass Cover", "sergiogoncalvsbass369"),
    SIMON_SKIPPER("Simon Skipper", "SimonSkipper"),
    TOM_BORNEMANN("Tom Bornemann", "basscination"),
    ROBERTO_YANGUS("Roberto Yanguas", "robertoyanguas8990"),
    VX_MATTHEW("VX Matthew", "vxmatthew7270"),
    YELLOW_TABS("Yellow Tabs", "YellowTabs");

    public final String label;

    /**
     * Channel id
     */
    @JsonValue
    public final String id;

    Channel(String name, String id) {
        this.label = name;
        this.id = id;
    }
}
