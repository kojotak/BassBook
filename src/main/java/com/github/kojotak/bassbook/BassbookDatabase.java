package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.github.kojotak.bassbook.data.Channel.*;
import static com.github.kojotak.bassbook.data.Feel.SWING;
import static com.github.kojotak.bassbook.data.Song.name;
import static com.github.kojotak.bassbook.data.Technique.*;
import static com.github.kojotak.bassbook.data.Tuning.*;
import static java.util.EnumSet.of;

@Service
public class BassbookDatabase {

    private final List<Author> authors = prepareAuthors();

    public Collection<Author> getAuthors() {
        return authors;
    }

    private List<Author> prepareAuthors(){
        return List.of(
                new Author("The Cranberries", List.of(
                        name("Zombie").youtubeAnd(COVERSOLUTIONS, "u9Zuoepny2Y").youtube(BRAND73, "oqe6rWKOvXk"),
                        name("Promises").youtube(ROBERTO_YANGUS, "a0VlH1DhdIY"),
                        name("Linger").youtube(COVERSOLUTIONS, "T1JCwqngEl0"),
                        name("Animal instinct").youtube(BASSCOVERS88, "x1Ifi28ac4E"),
                        name("Just my imagination").youtube(BASSCOVERS88, "ZQRerIJ6Jkw"))),
                new Author("R.E.M", List.of(
                        name("Man on the Moon").youtubeAnd(BRAND73, "yJSJjzp8y7g").youtubeAnd(LOVE_PEACE_BASS, "Ib1gKQi7riw").youtube(GEORDIE_BASSIST, "H_fPqhRbdDc"),
                        name("What's the frequency, Kenneth?").youtube(BRAND73, "rHRnfD6nhw0"),
                        name("Drive").youtube(CARLOS_CARLESI, "iIoUexEa5xg", of(DOUBLE_STOP)),
                        name("The sidewinder sleeps tonite").youtube(GEORDIE_BASSIST, "i-9aMVXPNvc"),
                        name("Orange crush").youtube(BRAND73, "gs2Wu1VSWWc", of(SLIDE, HAMMER_ON)),
                        name("Daysleeper").meter(6, 8).youtube(BRAND73, "PGZwLdEj5fg", of(SLIDE)),
                        name("Loosing my religion").youtube(EUBASS, "E9oj-pLkmtk"),
                        name("The one I love").youtube(BRAND73, "QO2zCg3Yj2o", of(DOUBLE_STOP)),
                        name("Electrolite").youtube(GEORDIE_BASSIST, "CTepfHmwkCg"),
                        name("Imitation of Life").youtube(EUBASS, "vN2feWmPC9Y"))),
                new Author("Red hot chilli peppers", List.of(
                        name("Californication").youtubeAnd(COVERSOLUTIONS, "vVSn1xindPM", of(HAMMER_ON, PULL_OFF)).youtubeAnd(NOCCO_CAT, "r_tKXpqi7UU", of(HAMMER_ON, PULL_OFF)).youtube(BASSCOVER_PETE, "Ml5Z0LMVUMg", of(HAMMER_ON, PULL_OFF)),
                        name("By the way").youtubeAnd(COVERSOLUTIONS, "vVSn1xindPM", DADG, of(HAMMER_ON, PULL_OFF)).youtube(YELLOW_TABS, "U0w51dkGZlY", DADG, of(HAMMER_ON, PULL_OFF)),
                        name("Under the bridge").youtubeAnd(COVERSOLUTIONS, "Tg-uUgEaovc", of(SLIDE, DOUBLE_STOP, GHOST_NOTE)).youtube(FUSILLI_JERRY, "ti40g3t-mF4", of(SLIDE, DOUBLE_STOP, GHOST_NOTE)),
                        name("Venice queen").youtube(LEO, "nOoaEqwQ6_8"),
                        name("Otherside").youtubeAnd(COVERSOLUTIONS, "XUTCU3v22GI").youtube(BASSCOVER_PETE, "4KWtEFOJjxM"),
                        name("Dani California").youtube(COVERSOLUTIONS, "0HuEUVNU-Co"),
                        name("Can't stop").youtubeAnd(COVERSOLUTIONS, "nNszif3eDTs", of(SLAP, GHOST_NOTE)).youtube(NATE_NAVARRO, "fA2XKuQAhnE", of(SLAP, GHOST_NOTE, SLIDE)),
                        name("Dark necessities").youtube(COVERSOLUTIONS, "FHohYlcdQkc", of(SLAP)),
                        name("Universally speaking").youtube(ANDREA_BERTIX, "aSHyHY4QmlM"),
                        name("Dosed").youtube(ANDREA_BERTIX, "RgFgw6kUJpk"),
                        name("Black summer").youtubeAnd(NOCCO_CAT, "w0PqTcMCATo").youtube(HARRY, "BgCIimrL3Go"),
                        name("Not the one").youtube(NOCCO_CAT, "bXey_3plyNA"),
                        name("Breaking the Girl").youtube(FUSILLI_JERRY, "nDqXtPpukWw"),
                        name("Easily").youtube(FUSILLI_JERRY, "nr8BvdmIGEM"))),
                new Author("Muse", List.of(
                        name("Psycho").youtube(COVERSOLUTIONS, "4CEzv6vZSiw", DADG, of(BEND)),
                        name("Uprising").feel(SWING).youtube(TOM_BORNEMANN, "SRK76vhoIAA", of(OCTAVES)),
                        name("Reapers").youtube(TOM_BORNEMANN, "CGnx6vnGA8o", DADG, of(HAMMER_ON)),
                        name("Time is running out").youtubeAnd(TOM_BORNEMANN, "BGji9IygCCw", of(SLIDE)).youtube(ANDRE_CARVALHO, "2CpQkmNe0Vg", of(SLIDE)),
                        name("Hysteria").bpm(93).youtubeAnd(NATE_NAVARRO, "2-BidwjmCgc").youtubeAnd(COVERSOLUTIONS, "QW0qlOSdkrM").youtube(FUSILLI_JERRY, "QTGkyIxF24w"))),
                new Author("U2", List.of(
                        name("Vertigo").youtubeAnd(BRAND73, "OULMZ3DC1WU").youtube(NOCCO_CAT, "F_evK5Ymt-c"),
                        name("Sweetest thing").youtubeAnd(BRAND73, "fvUBvZRh7LM").youtube(COVERSOLUTIONS, "6jP9me9oHzY"),
                        name("In God's country").youtubeAnd(BRAND73, "ExFYtgshjUc").youtube(LOVE_PEACE_BASS, "WmsJXmzRH90"),
                        name("I will follow").youtubeAnd(BRAND73, "Ko-O_rLgIVo", EbAbDbGb).youtube(LOVE_PEACE_BASS, "VuS_tDq98Uc", EbAbDbGb),
                        name("New Year's day").youtubeAnd(BRAND73, "lWV_wZK6_T8", EbAbDbGb).youtube(LOVE_PEACE_BASS, "l68dHHtB_a4", EADG),
                        name("Desire").youtube(BRAND73, "SrF5Sxrsamw", EbAbDbGb),
                        name("I still haven't found what I'm looking for").youtube(BRAND73, "5mwVzBe5G68", EbAbDbGb),
                        name("Sunday Bloody Sunday").youtube(COVERSOLUTIONS, "QDka2OB06LE", EbAbDbGb),
                        name("Beautiful Day").youtubeAnd(TOM_BORNEMANN, "XIjW_Gh0WiE").youtube(EUBASS, "O7GY3mchjtM", of(SLIDE)),
                        name("Bullet the blue sky").youtube(BRAND73, "1a8Un-qeLKQ", EbAbDbGb),
                        name("Zoo station").youtube(BRAND73, "X9F9MjY4Q5E"),
                        name("Even better than the real thing").youtube(BRAND73, "7U9SUlAl2Cw"),
                        name("City of blinding lights").youtube(EUBASS, "5LnvjrXIZq4", EbAbDbGb),
                        name("One tree hill").youtube(LOVE_PEACE_BASS, "w2EBY3ic7Ro"),
                        name("Red Hill mining town").youtube(LOVE_PEACE_BASS, "SS6075vGYjU"),
                        name("I threw a brick through a window").youtube(LOVE_PEACE_BASS, "fcLQceOpjWw"),
                        name("Heartland").youtube(LOVE_PEACE_BASS, "SKCfKedrMcI"),
                        name("Wire").youtube(LEO, "Csr5dtiSbeo", of(SLAP)),
                        name("Until the end of the the world").youtube(BASSCOVERS88, "ffhsInl8gBE"))),
                new Author("Phil Collins", List.of(
                        name("Another day in paradise").youtubeAnd(NOCCO_CAT, "bZ3CSRQ6e4U", of(SLIDE)).youtube(TOM_BORNEMANN, "BdpPpmMnjAo", of(SLIDE)),
                        name("Easy lover").youtubeAnd(FLORIAN_BASSO, "bqtjyr1Khsc").youtubeAnd(NOCCO_CAT, "r1z9_Pnorr4").youtubeAnd(HOW_TO_PLAY_BASSLINES, "7cnjN1BIv3A").youtube(BASSCOVER_PETE, "3CulOurL6mk"),
                        name("Don't Lose My Number").youtube(NOCCO_CAT, "FTQFPF2TbXc"),
                        name("In the air tonight").youtube(NICKZ911, "PZfLhQM_KIY"),
                        name("You Can't Hurry Love").youtube(BASS_MONKEY, "lnxWMAIC3FA"))),
                new Author("Queen", List.of(
                        name("Under pressure").youtube(COVERSOLUTIONS, "GdleLmsfFVg"),
                        name("Bohemian rhapsody").youtubeAnd(COVERSOLUTIONS, "qJVyHsJ2uEs").youtube(FUSILLI_JERRY, "zPfyI4ASn-c", EbAbDbGb),
                        name("The show must go on").youtube(PIANOPRINTER, "Pv_GFppKkbs"))),
                new Author("Nina Simone", List.of(name("Feeling good").youtube(HARRY, "qs9KVyJnKIU"))),
                new Author("Ray Charles", List.of(
                        name("Hit the road, Jack").youtubeAnd(HARRY, "M9NNOtMgAhk").youtube(GEORDIE_BASSIST, "fDxXvMPc4yQ"))),

                new Author("Billy Idol", List.of(
                        name("White wedding").youtubeAnd(HARRY, "TqA_IWPuGcU").youtubeAnd(BRAND73, "wMPEaVGX_7w").youtube(TOM_BORNEMANN, "bQ6aCLqVkrw"),
                        name("Rebel Yell").youtube(FUSILLI_JERRY, "u3nrN2-A_6g"))),
                new Author("Talking heads", List.of(name("Psycho killer").youtubeAnd(HARRY, "SNZHCz4rzKA").youtubeAnd(NOCCO_CAT, "m32-etMoOMY", of(STACCATO)).youtube(COVERSOLUTIONS, "6NpufkkmNmo"))),
                new Author("The White stripes", List.of(name("Seven nation army").youtubeAnd(HARRY, "6_7VrkrUuKc").youtube(ANDRE_CARVALHO, "adrcarvalho93"))),
                new Author("The Black eyed peas", List.of(name("Let's get it started").youtube(HARRY, "iz89AiRQhE0"))),
                new Author("Sia", List.of(name("Snowman").meter(6, 8).youtube(NOCCO_CAT, "KSye_cosGI4"))),
                new Author("The Beatles", List.of(
                        name("Lady Madonna").youtube(BRAND73, "YxVCt81YNOE"),
                        name("Something").youtube(NOCCO_CAT, "8YniwKLqZEc"),
                        name("Norwegian Wood").meter(6, 8).youtube(TOM_BORNEMANN, "ra9qUqa2UWI"),
                        name("Hey Jude").youtube(NOCCO_CAT, "89ElXR60JR0"))),
                new Author("The Prodigy", List.of(name("Climbatize").youtube(CARLOS_CARLESI, "dqR-t46ZtdA"))),
                new Author("Rammstein", List.of(
                        name("Du hast").youtubeAnd(COVERSOLUTIONS, "FryMBp0whO0", of(SLIDE)).youtube(YELLOW_TABS, "RCHu29Dp9Kg", of(SLIDE)),
                        name("Sonne").youtube(BASSCOVERS88, "ARJRPZfKTvM", DADG),
                        name("Deutschland").youtube(ROBERTO_YANGUS, "skjjiZZyQVg", CGCF),
                        name("Radio").youtube(ROBERTO_YANGUS, "EERNqdacpQo", CGCF),
                        name("Spieluhr").youtube(BASS_MONKEY, "1JNJt_w7GWA", DADG))),
                new Author("Adele", List.of(
                        name("Rolling in the deep").youtube(NOCCO_CAT, "IukN_9-d9mg"),
                        name("Set fire to the rain").youtube(NOCCO_CAT, "_V5kJGfsTvg"),
                        name("Skyfall").youtube(NOCCO_CAT, "7JMdaKXArxY"))),
                new Author("Coldplay", List.of(name("Yellow").youtube(EUBASS, "hTnGMOT76lk", of(SLIDE)))),
                new Author("System of a Down", List.of(name("Toxicity").meter(6, 8).youtubeAnd(EUBASS, "fORp9OK7wys", CGCF).youtube(COVERSOLUTIONS, "G_3Aze81cf0", CGCF))),
                new Author("Gorillaz", List.of(name("Feel Good Inc.").youtube(NOCCO_CAT, "g4pCrlkUUn4", EbAbDbGb))),
                new Author("Lenny Kravitz", List.of(name("Honey").youtube(NOCCO_CAT, "P-aXqEShNwA"))),
                new Author("Al Green", List.of(name("Let's Stay Together").youtube(NOCCO_CAT, "75Me-AY6Ia8"))),
                new Author("Sheryl Crow", List.of(name("Tomorrow never dies").meter(12, 8).youtube(NOCCO_CAT, "ffECgN2Mtjk"))),
                new Author("Pink Floyd", List.of(
                        name("Money").meter(7, 4).youtubeAnd(BRAND73, "L8xus4cWjyw").youtubeAnd(NATE_NAVARRO, "19FgY3o09Ng").youtube(FOTIS_TOUMANIDES, "TbVpme7Z0qA"),
                        name("Another Brick in the Wall").youtubeAnd(YELLOW_TABS, "Rm6Qm45Q-OI", DADG).youtube(BRAND73, "bW2MasJ9cX4", DADG),
                        name("Wish You Were Here").youtube(BRAND73, "tIwORLgt7Yw"),
                        name("Comfortably Numb").youtubeAnd(BRAND73, "a-G87UTs2BA").youtube(BASSCOVER_PETE, "ksUbAqkuCtk"))),
                new Author("Toto", List.of(
                        name("Africa").feel(Feel.SWING).youtube(COVERSOLUTIONS, "G_gSS7QTCM8", of(STACCATO)),
                        name("Rosanna").feel(SWING).youtube(NOCCO_CAT, "5FNmHvtiFVU"))),
                new Author("Earth, Wind & Fire", List.of(
                        name("Let's Groove").youtube(COVERSOLUTIONS, "qs1j9Uhm2JI", of(STACCATO)))),
                new Author("Doors", List.of(
                        name("Riders on the Storm").youtube(HARRY, "KFgfApQwF5Y"))),
                new Author("Foo Fighters", List.of(
                        name("Learn To Fly").youtubeAnd(BRAND73, "ZUXH7cA-dLc").youtube(COVERSOLUTIONS, "L8HDRJzA0fo"),
                        name("Everlong").youtube(COVERSOLUTIONS, "IB8ZOQZK3dA", DADG))),
                new Author("Linkin Park", List.of(
                        name("Heavy Is The Crown").youtube(COVERSOLUTIONS, "IhIiZl7ZOk4"))),
                new Author("AC/DC", List.of(
                        name("You Shook Me All Night Long").youtube(BRAND73, "_Zp-OB65O68"),
                        name("Hell's Bells").youtube(BRAND73, "-kbXnGwUSt0"),
                        name("Back in Black").youtubeAnd(TOM_BORNEMANN, "FDr2BNNGJ4g").youtube(BRAND73, "G4EwnokMswU"),
                        name("Shot Down In Flames").youtube(BRAND73, "PL-xiym0UMU"),
                        name("Touch Too Much").youtube(TOM_BORNEMANN, "Nj-oHGzU0Bc"),
                        name("For Those About To Rock").youtube(BRAND73, "ihTX8QAwru0"),
                        name("Shoot To Thrill").youtube(SAMBOAT_MUSIC, "zbXe2c4gdjQ"),
                        name("Thunderstruck").youtube(SAMBOAT_MUSIC, "vNGpLFnX_Xc"),
                        name("Highway to Hell").youtube(BRAND73, "v8wN90m8FBg"))),
                new Author("One Republic", List.of(
                        name("Counting Stars").youtube(BRAND73, "kHbfIXcO9Bs"))),
                new Author("Fleetwood Mac", List.of(
                        name("The Chain").youtubeAnd(HARRY, "B_MjFaEpzPU").youtubeAnd(NOCCO_CAT, "u3G3LJZAI30").youtubeAnd(TOM_BORNEMANN, "GTj3taqE1VU").youtube(FUSILLI_JERRY, "tYxdrqcg2_o"),
                        name("Dreams").youtubeAnd(NOCCO_CAT, "rgj5Vox5kkQ").youtubeAnd(COVERSOLUTIONS, "rgj5Vox5kkQ").youtubeAnd(FUSILLI_JERRY, "0KEIhvHzp0M").youtube(BASSCOVER_PETE, "tndBbFSuBtc"))),
                new Author("Jefferson Airplane", List.of(
                        name("White rabbit").youtube(COVERSOLUTIONS, "2m2hOkD7t8g", of(TRIPLET, SLIDE, DOUBLE_STOP)))),
                new Author("Michael Jackson", List.of(
                        name("Billy Jean").youtube(HARRY, "aeFT46ahI_o"),
                        name("Beat it").youtube(HARRY, "o5GkMoetGnw", DADG, of(HAMMER_ON)),
                        name("Bad").youtube(YELLOW_TABS, "Y65oltkZ5NA", of(SLIDE)))),
                new Author("Rage against the machine", List.of(
                        name("Killing in the name").youtubeAnd(TOM_BORNEMANN, "vZVDrlZbtO0", DADG, of(HAMMER_ON)).youtube(COVERSOLUTIONS, "oFicPQh0NtQ", DADG, of(HAMMER_ON)),
                        name("Guerrilla Radio").youtube(NOCCO_CAT, "ZfMS_gF1_HM"))),
                new Author("Dua Lipa", List.of(
                        name("Break my heart").youtube(YELLOW_TABS, "6s5vAsHiZdo", of(SLIDE, OCTAVES)))),
                new Author("Bon Jovi", List.of(
                        name("It's my life").youtubeAnd(YELLOW_TABS, "pRtzYkTQEMI", EADG).youtube(YELLOW_TABS, "JxoynLubXzM", HEADG))),
                new Author("Guano Apes", List.of(
                        name("Open your eyes").youtube(VX_MATTHEW, "Oimv05FB87c", of(SLAP, HAMMER_ON, GHOST_NOTE)))),
                new Author("Green Day", List.of(
                        name("Troubled times").youtube(TOM_BORNEMANN, "1UA9jy8RnSY"),
                        name("Warning").youtube(BRAND73, "1zPXl-GSKu0"),
                        name("Boulevard of broken dreams").youtube(BRAND73, "JKMrv55Lqw0"))),
                new Author("LaBelle", List.of(
                        name("Lady Marmellade").youtube(NOCCO_CAT, "0yRf2JGSvFM"))),
                new Author("Pharell Williams", List.of(
                        name("Happy").youtube(NOCCO_CAT, "swunHs4Ys5s"))),
                new Author("Pearl Jam", List.of(
                        name("Do the evolution").youtube(BRAND73, "lnEY7hHDzfQ"))),
                new Author("Led Zeppelin", List.of(
                        name("Kashmir").youtube(LOVE_PEACE_BASS, "oxNfFgtvoao"),
                        name("Immigrant Song").youtube(FUSILLI_JERRY, "26f9Rn0PzVk"))),
                new Author("Metallica", List.of(
                        name("For whom the bell tolls").youtube(LOVE_PEACE_BASS, "SlG55fpmEck"),
                        name("Until It Sleeps").youtube(BASS_MONKEY, "2N0DvTmRP-M", EbAbDbGb))),
                new Author("Journey", List.of(
                        name("Separate Ways (Worlds Apart)").youtube(NOCCO_CAT, "Yo6ZR77sJbs"))),
                new Author("Nightwish", List.of(
                        name("Nemo").youtubeAnd(BASSCOVERS88, "dElm3ayXSGU", DGCF, of(DOUBLE_STOP)).youtubeAnd(FOTIS_TOUMANIDES, "VKttyeO2FvE", DGCF).youtubeAnd(ROBERTO_YANGUS, "dElm3ayXSGU", DGCF, of(DOUBLE_STOP)).youtube(BASS_MONKEY, "6qVR-HVf55Y", CGCF, of(DOUBLE_STOP)),
                        name("Amaranth").youtubeAnd(FOTIS_TOUMANIDES, "1ueJpaQIqKQ", DGCF, of(BEND)).youtube(BASS_MONKEY, "C_j1MDCOAS4", DGCF, of(BEND)),
                        name("Wish I Had an Angel").youtube(BASS_MONKEY, "_dJIRuoYnjQ", DADG),
                        name("Bye Bye Beautiful").youtube(BASS_MONKEY, "lLDj9jwSF3U", DGCF))),
                new Author("Aerosmith", List.of(
                        name("Livin' on the edge").youtube(BRAND73, "0535hOPcFGE", DADG, of(SLIDE)))),
                new Author("Deep Purple", List.of(
                        name("Perfect strangers").youtubeAnd(TOM_BORNEMANN, "cKm9082w0Cc").youtube(FOTIS_TOUMANIDES, "n4-oUp6wNb0"),
                        name("Black night").youtube(FOTIS_TOUMANIDES, "FStaDA88VYg"),
                        name("Highway Star").youtube(FUSILLI_JERRY, "5S3XKs7Hn2k"),
                        name("Smoke On The Watter").youtube(BASSCOVER_PETE, "AXdUIfTEEvw"))),
                new Author("Sting", List.of(
                        name("Message in a bottle").youtube(NOCCO_CAT, "WsQaRMrbwQM"),
                        name("Fields of gold").youtube(NOCCO_CAT, "gFe0hriVZNs"),
                        name("Seven days").youtube(NOCCO_CAT, "Ut-48LPSblE"),
                        name("Englishman in New York").youtube(NOCCO_CAT, "S6iMjqiExDU"))),
                new Author("Deftones", List.of(
                        name("Be quiet and drive").youtube(SIMON_SKIPPER, "onntnYKCufw", CsGsCsFs),
                        name("My Own Summer").youtube(BASS_GUITAR_IQ, "4e88DLfAtvA", CsGsCsFs))),
                new Author("Arctic Monkeys", List.of(
                        name("Do I Wanna Know").youtube(NOCCO_CAT, "Y9_sItWDKIM", of(SLIDE)))),
                new Author("Aretha Franklin", List.of(
                        name("I Say A Little Prayer").youtube(NOCCO_CAT, "woJUs2Wcths"))),
                new Author("Britney Spears", List.of(
                        name("Toxic").youtube(NOCCO_CAT, "-ZpfCtsNY6g"))),
                new Author("Bruno Mars", List.of(
                        name("Treasure").youtubeAnd(NOCCO_CAT, "WFuQaAZh-xo", of(SLAP, HAMMER_ON)).youtube(HOW_TO_PLAY_BASSLINES, "CwNk7ujk2FY", of(SLIDE)),
                        name("Locked out of heaven").youtube(HOW_TO_PLAY_BASSLINES, "1cN35xg0TYk"))),
                new Author("Jamiroquai", List.of(
                        name("Time Won't Wait").youtube(NOCCO_CAT, "5XXuPHD17kM"))),
                new Author("Survivor", List.of(
                        name("Eye of the tiger").youtube(NOCCO_CAT, "ZOr9oMqKn7c"))),
                new Author("Radiohead", List.of(
                        name("Creep").youtube(NOCCO_CAT, "0hBtVjbOdc4"))),
                new Author("Tracy Chapman", List.of(
                        name("Give Me One Reason").youtube(NOCCO_CAT, "YOWLZ4bZ8sQ"))),
                new Author("Police", List.of(
                        name("King Of Pain").youtube(BRAND73, "iL1yt_gWOEU"),
                        name("Synchronicity II").bpm(157).youtube(BRAND73, "Dsrfn-K11hY", of(PICK, OCTAVES)),
                        name("Message in the Bottle").youtubeAnd(NOCCO_CAT, "WsQaRMrbwQM").youtube(BRAND73, "6VG5e8dQzwM", of(SLIDE)),
                        name("Invisible Sun").youtube(BRAND73, "Wp3026sDfao"),
                        name("Walking on the Moon").youtube(BRAND73, "TOQaI_0j47g"),
                        name("Spirits of the Material World").youtube(BRAND73, "AUi8jIkuU60"))),
                new Author("P!nk", List.of(
                        name("So What").youtube(ANDRE_CARVALHO, "U13zbgFK4yA"))),
                new Author("Puscifer", List.of(
                        name("The Remedy").bpm(180).meter(5, 4).youtube(COVERSOLUTIONS, "ccVFYsxo4C0", of(PICK)))),
                new Author("Porcupine Tree", List.of(
                        name(".3").youtube(LEO, "ZFLcc5XgamA", DADG),
                        name("Strip The Soul").youtube(LEO, "tj_5-UZWEQM", DADG))),
                new Author("Boney M", List.of(
                        name("Rasputin").youtube(COVERSOLUTIONS, "XdGQoq7PzVk"))),
                new Author("Sade", List.of(
                        name("Smooth operator").youtubeAnd(BRAND73, "7Ikvll2zMxI").youtubeAnd(FUSILLI_JERRY, "kZYxsM6PwBI").youtube(BASSCOVER_PETE, "vwsIIbxW73k"))),
                new Author("Chris Isaak", List.of(
                        name("Wicked game").youtube(GEORDIE_BASSIST, "a7s3F9VBuww"))),
                new Author("Ben E. King", List.of(
                        name("Stand By Me").youtube(HARRY, "W-w-wmXWt3k"))),
                new Author("MC Hammer", List.of(
                        name("You Can't Touch This").youtube(HARRY, "81kPwiF9_t0"))),
                new Author("Within Temptation", List.of(
                        name("Running Up That Hill").youtube(BASS_MONKEY, "1WV8EWL0vUs"))),
                new Author("Bil Withers", List.of(
                        name("Ain't no Sunshine").youtubeAnd(FUSILLI_JERRY, "38Pbv9tDj3A").youtube(HARRY, "b1H6wW5RzEA"))),
                new Author("Wham!", List.of(
                        name("Last Christmas").frequency(425).youtube(COVERSOLUTIONS, "5E0QHO22ls8", of(HAMMER_ON, STACCATO)))),
                new Author("Nirvana", List.of(
                        name("In Bloom").youtubeAnd(HOW_TO_PLAY_BASSLINES, "4mb_6mhK_5M").youtube(COVERSOLUTIONS, "E6iXRdTKtV4"),
                        name("Smells Like Teen Spirit").youtube(COVERSOLUTIONS, "pmtvOOI-pyU"),
                        name("Come As You Are").youtube(COVERSOLUTIONS, "rWHkrYRhOzw", DGCF),
                        name("Heart Shaped Box").youtube(BRAND73, "ng3-YZs-7x4", DbAbDbGb),
                        name("Polly").youtube(BRAND73, "YHAm0U7CRiA"),
                        name("The Man Who Sold The World").youtube(BRAND73, "wRrEK0Tu3vg", EbAbDbGb),
                        name("Lithium").youtube(EUBASS, "3giBt4c55hU"))),
                new Author("Kate Bush", List.of(
                        name("Running Up That Hill").youtube(BASSCOVER_PETE, "SjbYAELECqQ")))
        );
    }

}
