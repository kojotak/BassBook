package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection  ;
import java.util.Comparator;
import java.util.List;

import static com.github.kojotak.bassbook.data.Channel.*;
import static com.github.kojotak.bassbook.data.Feel.SWING;
import static com.github.kojotak.bassbook.data.Technique.*;
import static com.github.kojotak.bassbook.data.Tuning.*;
import static java.util.EnumSet.of;

@Service
public class BassbookDatabase implements ApplicationListener<ContextRefreshedEvent> {

    private final List<Song> songs = new ArrayList<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var context = event.getApplicationContext();

        songs.addAll(context.getBeansOfType(Song.class).values());

        for(var list : context.getBeansOfType(List.class).values()){
            var filtered = list.stream().filter(Song.class::isInstance).toList();
            songs.addAll(filtered);
        }

        songs.sort(Comparator.comparing(Song::name));
    }

    public Collection<Song> getSongs() {
        return songs;
    }

    @Bean
    private List<Song> cranberries() {
        return Song.from(Author.THE_CRANBERRIES).
                name("Zombie").youtube(COVERSOLUTIONS, "u9Zuoepny2Y").next().
                name("Promises").youtube(ROBERTO_YANGUS, "a0VlH1DhdIY").next().
                name("Linger").youtube(COVERSOLUTIONS, "T1JCwqngEl0").
                buildAll();
    }

    @Bean
    private List<Song> rem() {
        return Song.from(Author.REM).
                name("Man on the Moon").youtube(BRAND73, "yJSJjzp8y7g").next().
                name("What's the frequency, Kenneth?").youtube(BRAND73, "rHRnfD6nhw0").next().
                name("Drive").youtube(CARLOS_CARLESI, "iIoUexEa5xg", of(DOUBLE_STOP)).next().
                name("The sidewinder sleeps tonite").youtube(GEORDIE_BASSIST, "i-9aMVXPNvc").next().
                name("Orange crush").youtube(BRAND73, "gs2Wu1VSWWc", of(SLIDE, HAMMER_ON)).next().
                name("Daysleeper").meter(6,8).youtube(BRAND73, "PGZwLdEj5fg", of(SLIDE)).
                name("Loosing my religion").youtube(EUBASS, "E9oj-pLkmtk").
                buildAll();
    }

    @Bean
    private List<Song> rhcp() {
        return Song.from(Author.RHCP).
                name("Californication")
                    .youtube(COVERSOLUTIONS, "vVSn1xindPM", of(HAMMER_ON, PULL_OFF))
                    .youtube(NOCCO_CAT, "r_tKXpqi7UU", of(HAMMER_ON, PULL_OFF))
                    .next().
                name("By the way")
                    .youtube(COVERSOLUTIONS, "vVSn1xindPM", DADG, of(HAMMER_ON, PULL_OFF))
                    .youtube(YELLOW_TABS, "U0w51dkGZlY", DADG, of(HAMMER_ON, PULL_OFF))
                .next().
                name("Under the bridge").youtube(COVERSOLUTIONS, "Tg-uUgEaovc", of(SLIDE, DOUBLE_STOP, GHOST_NOTE)).next().
                name("Venice queen").youtube(LEO, "nOoaEqwQ6_8").next().
                name("Otherside").youtube(COVERSOLUTIONS, "XUTCU3v22GI").next().
                name("Dani California").youtube(COVERSOLUTIONS, "0HuEUVNU-Co").next().
                name("Can't stop").youtube(COVERSOLUTIONS, "nNszif3eDTs", of(SLAP)).next().
                name("Dark necessities").youtube(COVERSOLUTIONS, "FHohYlcdQkc", of(SLAP)).next().
                name("Universally speaking").youtube(ANDREA_BERTIX, "aSHyHY4QmlM").next().
                name("Dosed").youtube(ANDREA_BERTIX, "RgFgw6kUJpk").next().
                name("Black summer").
                    youtube(NOCCO_CAT, "w0PqTcMCATo").
                    youtube(HARRY, "BgCIimrL3Go").
                buildAll();
    }


    @Bean
    private List<Song> muse(){
        return Song.from(Author.MUSE).
                name("Psycho").youtube(COVERSOLUTIONS, "4CEzv6vZSiw", DADG, of(BEND)).next().
                name("Uprising").feel(SWING).youtube(TOM_BORNEMANN, "SRK76vhoIAA", of(OCTAVES)).next().
                name("Reapers").youtube(TOM_BORNEMANN, "CGnx6vnGA8o", DADG, of(HAMMER_ON)).next().
                name("Time is running out").youtube(TOM_BORNEMANN, "BGji9IygCCw", of(SLIDE)).next().
                name("Hysteria").youtube(NATE_NAVARRO, "2-BidwjmCgc").youtube(COVERSOLUTIONS, "QW0qlOSdkrM")
                .buildAll();
    }

    @Bean
    private List<Song> u2() {
        return Song.from(Author.U2).
                name("Vertigo").youtube(BRAND73, "OULMZ3DC1WU").next().
                name("Sweetest thing").youtube(BRAND73, "fvUBvZRh7LM").next().
                name("In God's country").youtube(BRAND73, "ExFYtgshjUc").
                buildAll();
    }

    @Bean
    private List<Song> philCollins(){
        return Song.from(Author.PHIL_COLLINS).
                name("Another day in paradise").
                        youtube(NOCCO_CAT, "bZ3CSRQ6e4U", of(SLIDE)).
                        youtube(TOM_BORNEMANN, "BdpPpmMnjAo", of(SLIDE)).
                next().
                name("Easy lover").youtube(FLORIAN_BASSO, "bqtjyr1Khsc").next().
                name("Don't Lose My Number").youtube(NOCCO_CAT, "FTQFPF2TbXc").next().
                name("In the air tonight").youtube(NICKZ911, "PZfLhQM_KIY").
                buildAll();
    }

    @Bean
    private List<Song> queen() {
        return Song.from(Author.QUEEN).
                name("Under pressure").youtube(COVERSOLUTIONS, "GdleLmsfFVg").next().
                name("Bohemian rhapsody").youtube(COVERSOLUTIONS, "qJVyHsJ2uEs").next().
                name("The show must go on").youtube(PIANOPRINTER, "Pv_GFppKkbs").
                buildAll();
    }

    @Bean
    private Song ninaSimone() {
        return Song.from(Author.NINA_SIMONE).name("Feeling good").youtube(HARRY, "qs9KVyJnKIU").build();
    }

    @Bean
    private Song rayCharles() {
        return Song.from(Author.RAY_CHARLES).name("Hit the road, Jack").youtube(HARRY, "M9NNOtMgAhk").build();
    }

    @Bean
    private Song billyIdol() {
        return Song.from(Author.BILLY_IDOL).name("White wedding").youtube(HARRY, "TqA_IWPuGcU").build();
    }

    @Bean
    private Song talkingHeads() {
        return Song.from(Author.TALKING_HEADS).name("Psycho killer")
                .youtube(HARRY, "SNZHCz4rzKA")
                .youtube(NOCCO_CAT, "m32-etMoOMY", of(STACCATO))
                .youtube(COVERSOLUTIONS, "6NpufkkmNmo")
                .build();
    }

    @Bean
    private Song whiteStripes() {
        return Song.from(Author.THE_WHITE_STRIPES).name("Seven nation army").youtube(HARRY, "6_7VrkrUuKc").build();
    }

    @Bean
    private Song blackEyedPeas() {
        return Song.from(Author.THE_BLACK_EYED_PEAS).name("Let's get it started").youtube(HARRY, "iz89AiRQhE0").build();
    }

    @Bean
    private Song sia() {
        return Song.from(Author.SIA).name("Snowman").meter(6,8).youtube(NOCCO_CAT, "KSye_cosGI4").build();
    }

    @Bean
    private Song beatles(){
        return Song.from(Author.THE_BEATLES).name("Lady Madonna").youtube(BRAND73, "YxVCt81YNOE").build();
    }

    @Bean
    private Song prodigy(){
        return Song.from(Author.THE_PRODIGY).name("Climbatize").youtube(CARLOS_CARLESI, "dqR-t46ZtdA").build();
    }

    @Bean
    private Song rammstein(){
        return Song.from(Author.RAMMSTEIN)
                .name("Du hast")
                    .youtube(COVERSOLUTIONS, "FryMBp0whO0", of(SLIDE))
                    .youtube(YELLOW_TABS, "RCHu29Dp9Kg", of(SLIDE))
                .build();
    }

    @Bean
    private Song adele(){
        return Song.from(Author.ADELE)
                .name("Rolling in the deep").youtube(NOCCO_CAT, "IukN_9-d9mg")
                .name("Set fire to the rain").youtube(NOCCO_CAT, "_V5kJGfsTvg")
                .build();
    }

    @Bean
    private Song coldplay(){
        return Song.from(Author.COLDPLAY).name("Yellow").youtube(EUBASS, "hTnGMOT76lk", of(SLIDE)).build();
    }

    @Bean
    private Song systemOfADown(){
        return Song.from(Author.SOAD).name("Toxicity").meter(6, 8)
                .youtube(EUBASS, "fORp9OK7wys", CGCF)
                .youtube(COVERSOLUTIONS, "G_3Aze81cf0", CGCF)
                .build();
    }

    @Bean
    private Song gorillaz(){
        return Song.from(Author.GORILLAZ)
                .name("Feel Good Inc.").youtube(NOCCO_CAT, "g4pCrlkUUn4", EbAbDbGb)
                .build();
    }

    @Bean
    private Song lennyKravitz(){
        return Song.from(Author.LENNY_KRAVITZ).name("Honey").youtube(NOCCO_CAT, "P-aXqEShNwA").build();
    }

    @Bean
    private Song alGreen(){
        return Song.from(Author.AL_GREEN).name("Let's Stay Together").youtube(NOCCO_CAT, "75Me-AY6Ia8").build();
    }

    @Bean
    private Song sherylCrow(){
        return Song.from(Author.SHERYL_CROW).name("Tomorrow never dies").meter(12, 8)
                .youtube(NOCCO_CAT, "ffECgN2Mtjk").build();
    }

    @Bean
    private List<Song> pinkFloyd(){
        return Song.from(Author.PINK_FLOYD)
                .name("Money").meter(7, 4)
                    .youtube(BRAND73, "L8xus4cWjyw")
                    .youtube(NATE_NAVARRO, "19FgY3o09Ng")
                .next()
                .name("Another Brick in the Wall").youtube(YELLOW_TABS, "Rm6Qm45Q-OI")
                .buildAll();
    }

    @Bean
    private Song toto(){
        return Song.from(Author.TOTO).name("Africa").feel(Feel.SWING)
                .youtube(COVERSOLUTIONS, "G_gSS7QTCM8", of(STACCATO)).build();
    }

    @Bean
    private Song earthWindFire(){
        return Song.from(Author.EARTH_WIND_FIRE).name("Let's Groove")
                .youtube(COVERSOLUTIONS, "qs1j9Uhm2JI", of(STACCATO)).build();
    }

    @Bean
    private Song doors(){
        return Song.from(Author.DOORS).name("Riders on the Storm")
                .youtube(HARRY, "KFgfApQwF5Y").build();
    }

    @Bean
    private List<Song> fooFighers(){
        return Song.from(Author.FOO_FIGHTERS)
                .name("Learn To Fly")
                    .youtube(BRAND73, "ZUXH7cA-dLc")
                    .youtube(COVERSOLUTIONS, "L8HDRJzA0fo")
                .next()
                .name("Everlong").youtube(COVERSOLUTIONS, "IB8ZOQZK3dA", DADG)
                .buildAll();
    }

    @Bean
    private Song linkinPark(){
        return Song.from(Author.LINKIN_PARK).name("Heavy Is The Crown").youtube(COVERSOLUTIONS, "IhIiZl7ZOk4").build();
    }

    @Bean
    private Song acdc(){
        return Song.from(Author.ACDC).name("You Shook Me All Night Long").youtube(BRAND73, "_Zp-OB65O68").build();
    }

    @Bean
    private Song oneRepublic(){
        return Song.from(Author.ONE_REPUBLIC).name("Counting Stars").youtube(BRAND73, "kHbfIXcO9Bs").build();
    }

    @Bean
    private Song fleetwoodMac() {
        return Song.from(Author.FLEETWOOD_MAC).name("The Chain")
                .youtube(HARRY, "B_MjFaEpzPU")
                .youtube(NOCCO_CAT, "u3G3LJZAI30")
                .youtube(TOM_BORNEMANN, "GTj3taqE1VU")
                .build();
    }

    @Bean
    private Song jeffersonAirplane(){
        return Song.from(Author.JEFFERSON_AIRPLANE).name("White rabbit")
                .youtube(COVERSOLUTIONS, "2m2hOkD7t8g", of(TRIPLET, SLIDE, DOUBLE_STOP))
                .build();
    }

    @Bean
    private List<Song> michaelJackson(){
        return Song.from(Author.MICHAEL_JACKSON)
                .name("Billy Jean")
                    .youtube(HARRY, "aeFT46ahI_o")
                .next().name("Beat it")
                    .youtube(HARRY, "o5GkMoetGnw", DADG, of(HAMMER_ON))
                .next().name("Bad")
                    .youtube(YELLOW_TABS, "Y65oltkZ5NA", of(SLIDE))
                .buildAll();
    }

    @Bean
    private Song ratm(){
        return Song.from(Author.RATM)
                .name("Killing in the name")
                .youtube(TOM_BORNEMANN, "vZVDrlZbtO0", DADG, of(HAMMER_ON))
                .youtube(COVERSOLUTIONS, "oFicPQh0NtQ", DADG, of(HAMMER_ON))
                .build();
    }

    @Bean
    private Song duaLipa(){
        return Song.from(Author.DUA_LIPA)
                .name("Break my heart")
                    .youtube(YELLOW_TABS, "6s5vAsHiZdo", of(SLIDE, OCTAVES))
                .build();
    }

    @Bean
    private Song bonJovi() {
        return Song.from(Author.BON_JOVI)
                .name("It's my life")
                .youtube(YELLOW_TABS, "pRtzYkTQEMI", EADG)
                .youtube(YELLOW_TABS, "JxoynLubXzM", HEADG)
                .build();
    }

    @Bean
    private Song guanoApes(){
        return Song.from(Author.GUANO_APES)
                .name("Open your eyes")
                .youtube(VX_MATTHEW, "Oimv05FB87c", of(SLAP, HAMMER_ON, GHOST_NOTE))
                .build();
    }

    @Bean
    private List<Song> greenDay(){
        return Song.from(Author.GREEN_DAY)
                    .name("Troubled times")
                    .youtube(TOM_BORNEMANN, "1UA9jy8RnSY")
                .next()
                    .name("Warning")
                    .youtube(BRAND73, "1zPXl-GSKu0")
                .next()
                    .name("Boulevard of broken dreams")
                    .youtube(BRAND73, "JKMrv55Lqw0")
                .buildAll();
    }

    @Bean
    private Song laBelle(){
        return Song.from(Author.LABELLE)
                .name("Lady Marmellade")
                .youtube(NOCCO_CAT, "0yRf2JGSvFM")
                .build();
    }

    @Bean
    private Song pharellWilliams(){
        return Song.from(Author.PHARELL_WILLIAMS)
                .name("Happy")
                .youtube(NOCCO_CAT, "swunHs4Ys5s")
                .build();
    }

    @Bean
    private Song pearlJam(){
        return Song.from(Author.PEARL_JAM)
                .name("Do the evolution")
                .youtube(BRAND73, "lnEY7hHDzfQ")
                .build();
    }

}
