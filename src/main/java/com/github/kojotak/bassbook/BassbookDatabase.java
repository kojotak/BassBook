package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static com.github.kojotak.bassbook.data.Channel.*;
import static com.github.kojotak.bassbook.data.Feel.SWING;
import static com.github.kojotak.bassbook.data.Song.song;
import static com.github.kojotak.bassbook.data.Technique.*;
import static com.github.kojotak.bassbook.data.Tuning.*;
import static java.util.EnumSet.of;

@Service
public class BassbookDatabase {

    private final List<Author> authors = prepareAuthors();

    public Collection<Author> getAuthors() {
        return authors;
    }

    private <T extends Named> List<T> orderedList(T ... items) {
        return Arrays.stream(items).sorted(Comparator.comparing(Named::getName)).toList();
    }

    private List<Author> prepareAuthors(){
        return orderedList(
                new Author("The Cranberries", orderedList(
                        song("Zombie")
                                .youtubeAnd(COVERSOLUTIONS, "u9Zuoepny2Y")
                                .youtube(BRAND73, "oqe6rWKOvXk"),
                        song("Promises").youtube(ROBERTO_YANGUS, "a0VlH1DhdIY"),
                        song("Linger").youtube(COVERSOLUTIONS, "T1JCwqngEl0"),
                        song("Animal instinct").youtube(BASSCOVERS88, "x1Ifi28ac4E"),
                        song("Just my imagination").youtube(BASSCOVERS88, "ZQRerIJ6Jkw"))),
                new Author("R.E.M", orderedList(
                        song("Man on the Moon").youtubeAnd(BRAND73, "yJSJjzp8y7g").youtubeAnd(LOVE_PEACE_BASS, "Ib1gKQi7riw").youtube(GEORDIE_BASSIST, "H_fPqhRbdDc"),
                        song("What's the frequency, Kenneth?").youtube(BRAND73, "rHRnfD6nhw0"),
                        song("Drive").youtube(CARLOS_CARLESI, "iIoUexEa5xg", of(DOUBLE_STOP)),
                        song("The sidewinder sleeps tonite").youtube(GEORDIE_BASSIST, "i-9aMVXPNvc"),
                        song("Orange crush").youtube(BRAND73, "gs2Wu1VSWWc", of(SLIDE, HAMMER_ON)),
                        song("Daysleeper").meter(6, 8).youtube(BRAND73, "PGZwLdEj5fg", of(SLIDE)),
                        song("Loosing my religion").youtube(EUBASS, "E9oj-pLkmtk"),
                        song("The one I love").youtube(BRAND73, "QO2zCg3Yj2o", of(DOUBLE_STOP)),
                        song("Electrolite").youtube(GEORDIE_BASSIST, "CTepfHmwkCg"),
                        song("Imitation of Life").youtube(EUBASS, "vN2feWmPC9Y"))),
                new Author("Red hot chilli peppers", orderedList(
                        song("Californication").youtubeAnd(COVERSOLUTIONS, "vVSn1xindPM", of(HAMMER_ON, PULL_OFF)).youtubeAnd(NOCCO_CAT, "r_tKXpqi7UU", of(HAMMER_ON, PULL_OFF)).youtube(BASSCOVER_PETE, "Ml5Z0LMVUMg", of(HAMMER_ON, PULL_OFF)),
                        song("By the way").youtubeAnd(COVERSOLUTIONS, "vVSn1xindPM", DADG, of(HAMMER_ON, PULL_OFF)).youtube(YELLOW_TABS, "U0w51dkGZlY", DADG, of(HAMMER_ON, PULL_OFF)),
                        song("Under the bridge").youtubeAnd(COVERSOLUTIONS, "Tg-uUgEaovc", of(SLIDE, DOUBLE_STOP, GHOST_NOTE)).youtube(FUSILLI_JERRY, "ti40g3t-mF4", of(SLIDE, DOUBLE_STOP, GHOST_NOTE)),
                        song("Venice queen").youtube(LEO, "nOoaEqwQ6_8"),
                        song("Otherside").youtubeAnd(COVERSOLUTIONS, "XUTCU3v22GI").youtube(BASSCOVER_PETE, "4KWtEFOJjxM"),
                        song("Dani California").youtube(COVERSOLUTIONS, "0HuEUVNU-Co"),
                        song("Can't stop").youtubeAnd(COVERSOLUTIONS, "nNszif3eDTs", of(SLAP, GHOST_NOTE)).youtube(NATE_NAVARRO, "fA2XKuQAhnE", of(SLAP, GHOST_NOTE, SLIDE)),
                        song("Dark necessities").youtube(COVERSOLUTIONS, "FHohYlcdQkc", of(SLAP)),
                        song("Universally speaking").youtube(ANDREA_BERTIX, "aSHyHY4QmlM"),
                        song("Dosed").youtube(ANDREA_BERTIX, "RgFgw6kUJpk"),
                        song("Black summer").youtubeAnd(NOCCO_CAT, "w0PqTcMCATo").youtube(HARRY, "BgCIimrL3Go"),
                        song("Not the one").youtube(NOCCO_CAT, "bXey_3plyNA"),
                        song("Breaking the Girl").youtube(FUSILLI_JERRY, "nDqXtPpukWw"),
                        song("Easily").youtube(FUSILLI_JERRY, "nr8BvdmIGEM"))),
                new Author("Muse", orderedList(
                        song("Psycho").youtube(COVERSOLUTIONS, "4CEzv6vZSiw", DADG, of(BEND)),
                        song("Uprising").feel(SWING).youtube(TOM_BORNEMANN, "SRK76vhoIAA", of(OCTAVES)),
                        song("Reapers").youtube(TOM_BORNEMANN, "CGnx6vnGA8o", DADG, of(HAMMER_ON)),
                        song("Time is running out").youtubeAnd(TOM_BORNEMANN, "BGji9IygCCw", of(SLIDE)).youtube(ANDRE_CARVALHO, "2CpQkmNe0Vg", of(SLIDE)),
                        song("Hysteria").bpm(93).youtubeAnd(NATE_NAVARRO, "2-BidwjmCgc").youtubeAnd(COVERSOLUTIONS, "QW0qlOSdkrM").youtube(FUSILLI_JERRY, "QTGkyIxF24w"))),
                new Author("U2", orderedList(
                        song("Angel of Harmel").youtube(BRAND73, "Nue7i2SP3Dk"),
                        song("One").youtube(BRAND73, "rCsijpH_XTo"),
                        song("Vertigo").youtubeAnd(BRAND73, "OULMZ3DC1WU").youtube(NOCCO_CAT, "F_evK5Ymt-c"),
                        song("Sweetest thing").youtubeAnd(BRAND73, "fvUBvZRh7LM").youtube(COVERSOLUTIONS, "6jP9me9oHzY"),
                        song("In God's country").youtubeAnd(BRAND73, "ExFYtgshjUc").youtube(LOVE_PEACE_BASS, "WmsJXmzRH90"),
                        song("I will follow").youtubeAnd(BRAND73, "Ko-O_rLgIVo", EbAbDbGb).youtube(LOVE_PEACE_BASS, "VuS_tDq98Uc", EbAbDbGb),
                        song("New Year's day").youtubeAnd(BRAND73, "lWV_wZK6_T8", EbAbDbGb).youtube(LOVE_PEACE_BASS, "l68dHHtB_a4", EADG),
                        song("Desire").youtube(BRAND73, "SrF5Sxrsamw", EbAbDbGb),
                        song("I still haven't found what I'm looking for").youtube(BRAND73, "5mwVzBe5G68", EbAbDbGb),
                        song("Sunday Bloody Sunday").youtube(COVERSOLUTIONS, "QDka2OB06LE", EbAbDbGb),
                        song("Beautiful Day").youtubeAnd(TOM_BORNEMANN, "XIjW_Gh0WiE").youtube(EUBASS, "O7GY3mchjtM", of(SLIDE)),
                        song("Bullet the blue sky").youtube(BRAND73, "1a8Un-qeLKQ", EbAbDbGb),
                        song("Zoo station").youtube(BRAND73, "X9F9MjY4Q5E"),
                        song("Even better than the real thing").youtube(BRAND73, "7U9SUlAl2Cw"),
                        song("City of blinding lights").youtube(EUBASS, "5LnvjrXIZq4", EbAbDbGb),
                        song("One tree hill").youtube(LOVE_PEACE_BASS, "w2EBY3ic7Ro"),
                        song("Red Hill mining town").youtube(LOVE_PEACE_BASS, "SS6075vGYjU"),
                        song("I threw a brick through a window").youtube(LOVE_PEACE_BASS, "fcLQceOpjWw"),
                        song("Heartland").youtube(LOVE_PEACE_BASS, "SKCfKedrMcI"),
                        song("Wire").youtube(LEO, "Csr5dtiSbeo", of(SLAP)),
                        song("Until the end of the the world").youtube(BASSCOVERS88, "ffhsInl8gBE"),
                        song("Staring At The Sun").youtube(BASS_MONKEY, "qvxGSf36VZg"))),
                new Author("Phil Collins", orderedList(
                        song("Another day in paradise").youtubeAnd(NOCCO_CAT, "bZ3CSRQ6e4U", of(SLIDE)).youtube(TOM_BORNEMANN, "BdpPpmMnjAo", of(SLIDE)),
                        song("Easy lover").youtubeAnd(FLORIAN_BASSO, "bqtjyr1Khsc").youtubeAnd(NOCCO_CAT, "r1z9_Pnorr4").youtubeAnd(HOW_TO_PLAY_BASSLINES, "7cnjN1BIv3A").youtube(BASSCOVER_PETE, "3CulOurL6mk"),
                        song("Don't Lose My Number").youtube(NOCCO_CAT, "FTQFPF2TbXc"),
                        song("In the air tonight").youtube(NICKZ911, "PZfLhQM_KIY"),
                        song("You Can't Hurry Love").youtube(BASS_MONKEY, "lnxWMAIC3FA"))),
                new Author("Genesis", orderedList(
                        song("Jesus He Knows Me").bpm(95).youtube(BASS_MONKEY, "Uk3AHXpd-lQ")
                )),
                new Author("Queen", orderedList(
                        song("Under pressure").youtube(COVERSOLUTIONS, "GdleLmsfFVg"),
                        song("Bohemian rhapsody").youtubeAnd(COVERSOLUTIONS, "qJVyHsJ2uEs").youtube(FUSILLI_JERRY, "zPfyI4ASn-c", EbAbDbGb),
                        song("The show must go on").youtube(PIANOPRINTER, "Pv_GFppKkbs"))),
                new Author("Nina Simone", orderedList(song("Feeling good").youtube(HARRY, "qs9KVyJnKIU"))),
                new Author("Ray Charles", orderedList(
                        song("Hit the road, Jack").youtubeAnd(HARRY, "M9NNOtMgAhk").youtube(GEORDIE_BASSIST, "fDxXvMPc4yQ"))),

                new Author("Billy Idol", orderedList(
                        song("White wedding").youtubeAnd(HARRY, "TqA_IWPuGcU").youtubeAnd(BRAND73, "wMPEaVGX_7w").youtube(TOM_BORNEMANN, "bQ6aCLqVkrw"),
                        song("Rebel Yell").youtube(FUSILLI_JERRY, "u3nrN2-A_6g"))),
                new Author("Talking heads", orderedList(
                        song("Psycho killer")
                                .youtubeAnd(HARRY, "SNZHCz4rzKA")
                                .youtubeAnd(BRAND73, "6QU4JQm4ZTY")
                                .youtubeAnd(NOCCO_CAT, "m32-etMoOMY", of(STACCATO))
                                .youtube(COVERSOLUTIONS, "6NpufkkmNmo"))),
                new Author("The White stripes", orderedList(song("Seven nation army").youtubeAnd(HARRY, "6_7VrkrUuKc").youtube(ANDRE_CARVALHO, "adrcarvalho93"))),
                new Author("The Black eyed peas", orderedList(song("Let's get it started").youtube(HARRY, "iz89AiRQhE0"))),
                new Author("Sia", orderedList(song("Snowman").meter(6, 8).youtube(NOCCO_CAT, "KSye_cosGI4"))),
                new Author("The Beatles", orderedList(
                        song("Lady Madonna").youtube(BRAND73, "YxVCt81YNOE"),
                        song("Something").youtube(NOCCO_CAT, "8YniwKLqZEc"),
                        song("Norwegian Wood").meter(6, 8).youtube(TOM_BORNEMANN, "ra9qUqa2UWI"),
                        song("Hey Jude").youtube(NOCCO_CAT, "89ElXR60JR0"))),
                new Author("The Prodigy", orderedList(song("Climbatize").youtube(CARLOS_CARLESI, "dqR-t46ZtdA"))),
                new Author("Rammstein", orderedList(
                        song("Du hast").youtubeAnd(COVERSOLUTIONS, "FryMBp0whO0", of(SLIDE)).youtube(YELLOW_TABS, "RCHu29Dp9Kg", of(SLIDE)),
                        song("Sonne").youtube(BASSCOVERS88, "ARJRPZfKTvM", DADG),
                        song("Deutschland").youtube(ROBERTO_YANGUS, "skjjiZZyQVg", CGCF),
                        song("Radio").youtube(ROBERTO_YANGUS, "EERNqdacpQo", CGCF),
                        song("Spieluhr").youtube(BASS_MONKEY, "1JNJt_w7GWA", DADG))),
                new Author("Adele", orderedList(
                        song("Rolling in the deep").youtube(NOCCO_CAT, "IukN_9-d9mg"),
                        song("Set fire to the rain").youtube(NOCCO_CAT, "_V5kJGfsTvg"),
                        song("Skyfall").youtube(NOCCO_CAT, "7JMdaKXArxY"))),
                new Author("Coldplay", orderedList(song("Yellow").youtube(EUBASS, "hTnGMOT76lk", of(SLIDE)))),
                new Author("System of a Down", orderedList(song("Toxicity").meter(6, 8).youtubeAnd(EUBASS, "fORp9OK7wys", CGCF).youtube(COVERSOLUTIONS, "G_3Aze81cf0", CGCF))),
                new Author("Gorillaz", orderedList(song("Feel Good Inc.").youtube(NOCCO_CAT, "g4pCrlkUUn4", EbAbDbGb))),
                new Author("Lenny Kravitz", orderedList(song("Honey").youtube(NOCCO_CAT, "P-aXqEShNwA"))),
                new Author("Al Green", orderedList(song("Let's Stay Together").youtube(NOCCO_CAT, "75Me-AY6Ia8"))),
                new Author("Sheryl Crow", orderedList(song("Tomorrow never dies").meter(12, 8).youtube(NOCCO_CAT, "ffECgN2Mtjk"))),
                new Author("Pink Floyd", orderedList(
                        song("Money").meter(7, 4).youtubeAnd(BRAND73, "L8xus4cWjyw").youtubeAnd(NATE_NAVARRO, "19FgY3o09Ng").youtube(FOTIS_TOUMANIDES, "TbVpme7Z0qA"),
                        song("Another Brick in the Wall").youtubeAnd(YELLOW_TABS, "Rm6Qm45Q-OI", DADG).youtube(BRAND73, "bW2MasJ9cX4", DADG),
                        song("Wish You Were Here").youtube(BRAND73, "tIwORLgt7Yw"),
                        song("Comfortably Numb").youtubeAnd(BRAND73, "a-G87UTs2BA").youtube(BASSCOVER_PETE, "ksUbAqkuCtk"))),
                new Author("Toto", orderedList(
                        song("Africa").feel(Feel.SWING).youtube(COVERSOLUTIONS, "G_gSS7QTCM8", of(STACCATO)),
                        song("Rosanna").feel(SWING).youtube(NOCCO_CAT, "5FNmHvtiFVU"))),
                new Author("Earth, Wind & Fire", orderedList(
                        song("Let's Groove").youtube(COVERSOLUTIONS, "qs1j9Uhm2JI", of(STACCATO)))),
                new Author("Doors", orderedList(
                        song("Riders on the Storm").youtube(HARRY, "KFgfApQwF5Y"))),
                new Author("Foo Fighters", orderedList(
                        song("Learn To Fly").youtubeAnd(BRAND73, "ZUXH7cA-dLc").youtube(COVERSOLUTIONS, "L8HDRJzA0fo"),
                        song("Everlong").youtube(COVERSOLUTIONS, "IB8ZOQZK3dA", DADG))),
                new Author("Linkin Park", orderedList(
                        song("Heavy Is The Crown").youtube(COVERSOLUTIONS, "IhIiZl7ZOk4"),
                        song("Faint").youtube(SAITO, "Q2fJ87JcI34", CsGsCsFs),
                        song("Given Up").bpm(200)
                                .youtubeAnd(SAITO, "oTfVyifmrlY", DADG)
                                .youtube(BASS_MONKEY, "OMKcsN7fqpw", DADG),
                        song("In The End").youtube(COVERSOLUTIONS, "6w_6rYWgC4U", BEAD))),
                new Author("AC/DC", orderedList(
                        song("You Shook Me All Night Long").youtube(BRAND73, "_Zp-OB65O68"),
                        song("Hell's Bells").youtube(BRAND73, "-kbXnGwUSt0"),
                        song("Back in Black").youtubeAnd(TOM_BORNEMANN, "FDr2BNNGJ4g").youtube(BRAND73, "G4EwnokMswU"),
                        song("Shot Down In Flames").youtube(BRAND73, "PL-xiym0UMU"),
                        song("Touch Too Much").youtube(TOM_BORNEMANN, "Nj-oHGzU0Bc"),
                        song("For Those About To Rock").youtube(BRAND73, "ihTX8QAwru0"),
                        song("Shoot To Thrill").youtube(SAMBOAT_MUSIC, "zbXe2c4gdjQ"),
                        song("Thunderstruck").youtube(SAMBOAT_MUSIC, "vNGpLFnX_Xc"),
                        song("Highway to Hell").youtube(BRAND73, "v8wN90m8FBg"))),
                new Author("One Republic", orderedList(
                        song("Counting Stars").youtube(BRAND73, "kHbfIXcO9Bs"))),
                new Author("Fleetwood Mac", orderedList(
                        song("The Chain").youtubeAnd(HARRY, "B_MjFaEpzPU").youtubeAnd(NOCCO_CAT, "u3G3LJZAI30").youtubeAnd(TOM_BORNEMANN, "GTj3taqE1VU").youtube(FUSILLI_JERRY, "tYxdrqcg2_o"),
                        song("Dreams").youtubeAnd(NOCCO_CAT, "rgj5Vox5kkQ").youtubeAnd(COVERSOLUTIONS, "rgj5Vox5kkQ").youtubeAnd(FUSILLI_JERRY, "0KEIhvHzp0M").youtube(BASSCOVER_PETE, "tndBbFSuBtc"))),
                new Author("Jefferson Airplane", orderedList(
                        song("White rabbit").youtube(COVERSOLUTIONS, "2m2hOkD7t8g", of(TRIPLET, SLIDE, DOUBLE_STOP)))),
                new Author("Michael Jackson", orderedList(
                        song("Billy Jean").youtube(HARRY, "aeFT46ahI_o"),
                        song("Beat it").youtube(HARRY, "o5GkMoetGnw", DADG, of(HAMMER_ON)),
                        song("Bad").youtube(YELLOW_TABS, "Y65oltkZ5NA", of(SLIDE)))),
                new Author("Rage against the machine", orderedList(
                        song("Killing in the name").youtubeAnd(TOM_BORNEMANN, "vZVDrlZbtO0", DADG, of(HAMMER_ON)).youtube(COVERSOLUTIONS, "oFicPQh0NtQ", DADG, of(HAMMER_ON)),
                        song("Guerrilla Radio").youtube(NOCCO_CAT, "ZfMS_gF1_HM"))),
                new Author("Dua Lipa", orderedList(
                        song("Break my heart").youtube(YELLOW_TABS, "6s5vAsHiZdo", of(SLIDE, OCTAVES)))),
                new Author("Bon Jovi", orderedList(
                        song("It's my life").youtubeAnd(YELLOW_TABS, "pRtzYkTQEMI", EADG).youtube(YELLOW_TABS, "JxoynLubXzM", BEADG))),
                new Author("Guano Apes", orderedList(
                        song("Open your eyes").youtube(VX_MATTHEW, "Oimv05FB87c", of(SLAP, HAMMER_ON, GHOST_NOTE)))),
                new Author("Green Day", orderedList(
                        song("Troubled times").youtube(TOM_BORNEMANN, "1UA9jy8RnSY"),
                        song("Warning").youtube(BRAND73, "1zPXl-GSKu0"),
                        song("Boulevard of broken dreams").youtube(BRAND73, "JKMrv55Lqw0"))),
                new Author("LaBelle", orderedList(
                        song("Lady Marmellade").youtube(NOCCO_CAT, "0yRf2JGSvFM"))),
                new Author("Pharell Williams", orderedList(
                        song("Happy").youtube(NOCCO_CAT, "swunHs4Ys5s"))),
                new Author("Pearl Jam", orderedList(
                        song("Do the evolution").youtube(BRAND73, "lnEY7hHDzfQ"))),
                new Author("Led Zeppelin", orderedList(
                        song("Kashmir").youtube(LOVE_PEACE_BASS, "oxNfFgtvoao"),
                        song("Immigrant Song").youtube(FUSILLI_JERRY, "26f9Rn0PzVk"))),
                new Author("Metallica", orderedList(
                        song("For whom the bell tolls").youtube(LOVE_PEACE_BASS, "SlG55fpmEck"),
                        song("Until It Sleeps").youtube(BASS_MONKEY, "2N0DvTmRP-M", EbAbDbGb))),
                new Author("Journey", orderedList(
                        song("Separate Ways (Worlds Apart)").youtube(NOCCO_CAT, "Yo6ZR77sJbs"))),
                new Author("Nightwish", orderedList(
                        song("Nemo").youtubeAnd(BASSCOVERS88, "dElm3ayXSGU", DGCF, of(DOUBLE_STOP)).youtubeAnd(FOTIS_TOUMANIDES, "VKttyeO2FvE", DGCF).youtubeAnd(ROBERTO_YANGUS, "dElm3ayXSGU", DGCF, of(DOUBLE_STOP)).youtube(BASS_MONKEY, "6qVR-HVf55Y", CGCF, of(DOUBLE_STOP)),
                        song("Amaranth").youtubeAnd(FOTIS_TOUMANIDES, "1ueJpaQIqKQ", DGCF, of(BEND)).youtube(BASS_MONKEY, "C_j1MDCOAS4", DGCF, of(BEND)),
                        song("Wish I Had an Angel").youtube(BASS_MONKEY, "_dJIRuoYnjQ", DADG),
                        song("Bye Bye Beautiful").youtube(BASS_MONKEY, "lLDj9jwSF3U", DGCF))),
                new Author("Aerosmith", orderedList(
                        song("Livin' on the edge").youtube(BRAND73, "0535hOPcFGE", DADG, of(SLIDE)))),
                new Author("Deep Purple", orderedList(
                        song("Perfect strangers").youtubeAnd(TOM_BORNEMANN, "cKm9082w0Cc").youtube(FOTIS_TOUMANIDES, "n4-oUp6wNb0"),
                        song("Black night").youtube(FOTIS_TOUMANIDES, "FStaDA88VYg"),
                        song("Highway Star").youtube(FUSILLI_JERRY, "5S3XKs7Hn2k"),
                        song("Smoke On The Watter").youtube(BASSCOVER_PETE, "AXdUIfTEEvw"))),
                new Author("Sting", orderedList(
                        song("Message in a bottle").youtube(NOCCO_CAT, "WsQaRMrbwQM"),
                        song("Fields of gold").youtube(NOCCO_CAT, "gFe0hriVZNs"),
                        song("Seven days").youtube(NOCCO_CAT, "Ut-48LPSblE"),
                        song("Englishman in New York").youtube(NOCCO_CAT, "S6iMjqiExDU"))),
                new Author("Deftones", orderedList(
                        song("Be quiet and drive").youtube(SIMON_SKIPPER, "onntnYKCufw", CsGsCsFs),
                        song("My Own Summer").youtube(BASS_GUITAR_IQ, "4e88DLfAtvA", CsGsCsFs))),
                new Author("Arctic Monkeys", orderedList(
                        song("Do I Wanna Know").youtube(NOCCO_CAT, "Y9_sItWDKIM", of(SLIDE)))),
                new Author("Aretha Franklin", orderedList(
                        song("I Say A Little Prayer").youtube(NOCCO_CAT, "woJUs2Wcths"))),
                new Author("Britney Spears", orderedList(
                        song("Toxic").youtube(NOCCO_CAT, "-ZpfCtsNY6g"))),
                new Author("Bruno Mars", orderedList(
                        song("Treasure")
                                .youtubeAnd(NOCCO_CAT, "WFuQaAZh-xo", of(SLAP, HAMMER_ON))
                                .youtube(HOW_TO_PLAY_BASSLINES, "CwNk7ujk2FY", of(SLIDE)),
                        song("Locked out of heaven").youtube(HOW_TO_PLAY_BASSLINES, "1cN35xg0TYk"))),
                new Author("Jamiroquai", orderedList(
                        song("Time Won't Wait").youtube(NOCCO_CAT, "5XXuPHD17kM"))),
                new Author("Survivor", orderedList(
                        song("Eye of the tiger").youtube(NOCCO_CAT, "ZOr9oMqKn7c"))),
                new Author("Radiohead", orderedList(
                        song("Creep").youtube(NOCCO_CAT, "0hBtVjbOdc4"))),
                new Author("Tracy Chapman", orderedList(
                        song("Give Me One Reason").youtube(NOCCO_CAT, "YOWLZ4bZ8sQ"))),
                new Author("Police", orderedList(
                        song("King Of Pain").youtube(BRAND73, "iL1yt_gWOEU"),
                        song("Synchronicity II").bpm(157).youtube(BRAND73, "Dsrfn-K11hY", of(PICK, OCTAVES)),
                        song("Message in the Bottle").youtubeAnd(NOCCO_CAT, "WsQaRMrbwQM").youtube(BRAND73, "6VG5e8dQzwM", of(SLIDE)),
                        song("Invisible Sun").youtube(BRAND73, "Wp3026sDfao"),
                        song("Walking on the Moon").youtube(BRAND73, "TOQaI_0j47g"),
                        song("Spirits of the Material World").youtube(BRAND73, "AUi8jIkuU60"))),
                new Author("P!nk", orderedList(
                        song("So What").youtube(ANDRE_CARVALHO, "U13zbgFK4yA"))),
                new Author("Puscifer", orderedList(
                        song("The Remedy").bpm(180).meter(5, 4).youtube(COVERSOLUTIONS, "ccVFYsxo4C0", of(PICK)))),
                new Author("Porcupine Tree", orderedList(
                        song(".3").youtube(LEO, "ZFLcc5XgamA", DADG),
                        song("Strip The Soul").youtube(LEO, "tj_5-UZWEQM", DADG))),
                new Author("Boney M", orderedList(
                        song("Rasputin").youtube(COVERSOLUTIONS, "XdGQoq7PzVk"))),
                new Author("Sade", orderedList(
                        song("Smooth operator").youtubeAnd(BRAND73, "7Ikvll2zMxI").youtubeAnd(FUSILLI_JERRY, "kZYxsM6PwBI").youtube(BASSCOVER_PETE, "vwsIIbxW73k"))),
                new Author("Chris Isaak", orderedList(
                        song("Wicked game").youtube(GEORDIE_BASSIST, "a7s3F9VBuww"))),
                new Author("Ben E. King", orderedList(
                        song("Stand By Me").youtube(HARRY, "W-w-wmXWt3k"))),
                new Author("MC Hammer", orderedList(
                        song("You Can't Touch This").youtube(HARRY, "81kPwiF9_t0"))),
                new Author("Within Temptation", orderedList(
                        song("Running Up That Hill").youtube(BASS_MONKEY, "1WV8EWL0vUs"))),
                new Author("Bil Withers", orderedList(
                        song("Ain't no Sunshine").youtubeAnd(FUSILLI_JERRY, "38Pbv9tDj3A").youtube(HARRY, "b1H6wW5RzEA"))),
                new Author("Wham!", orderedList(
                        song("Last Christmas").frequency(425).youtube(COVERSOLUTIONS, "5E0QHO22ls8", of(HAMMER_ON, STACCATO)))),
                new Author("Nirvana", orderedList(
                        song("In Bloom").youtubeAnd(HOW_TO_PLAY_BASSLINES, "4mb_6mhK_5M").youtube(COVERSOLUTIONS, "E6iXRdTKtV4"),
                        song("Smells Like Teen Spirit").youtube(COVERSOLUTIONS, "pmtvOOI-pyU"),
                        song("Come As You Are").youtube(COVERSOLUTIONS, "rWHkrYRhOzw", DGCF),
                        song("Heart Shaped Box").youtube(BRAND73, "ng3-YZs-7x4", DbAbDbGb),
                        song("Polly").youtube(BRAND73, "YHAm0U7CRiA"),
                        song("The Man Who Sold The World").youtube(BRAND73, "wRrEK0Tu3vg", EbAbDbGb),
                        song("About a Girl").youtube(BRAND73, "03vk9RoRQ0w"),
                        song("Lithium").youtube(EUBASS, "3giBt4c55hU"))),
                new Author("Kate Bush", orderedList(
                        song("Running Up That Hill").youtube(BASSCOVER_PETE, "SjbYAELECqQ"))),
                new Author("The Corrs", orderedList(
                        song("Only When I Sleep").youtube(KIMME08, "KHnI-pm1sc0")
                )),
                new Author("Alanis Morissete", orderedList(
                        song("Thank U").youtube(EUBASS, "pCfu6YKykIY", BEADG),
                        song("You Oughta Know")
                                .youtubeAnd(COVERSOLUTIONS, "D5YcPXLyn5M", EbAbDbGb, of(GHOST_NOTE))
                                .youtube(SERGIO_GONCALVS, "Z926-Q-FX1Q", EbAbDbGb),
                        song("Ironic").youtube(NOCCO_CAT, "I7CcAWIDrc0")
                ))
        );
    }

}
