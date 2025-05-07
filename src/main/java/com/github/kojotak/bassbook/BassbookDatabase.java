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
import static com.github.kojotak.bassbook.data.Tuning.DADG;
import static java.util.EnumSet.of;

@Service
public class BassbookDatabase implements ApplicationListener<ContextRefreshedEvent> {

    private final List<Song> songs = new ArrayList<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var context = event.getApplicationContext();

        songs.addAll(context.getBeansOfType(Song.class).values());

        for(var list : context.getBeansOfType(List.class).values()){
            songs.addAll(list);
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
                name("Drive").youtube(CARLOS_CARLESI, "iIoUexEa5xg").next().
                name("The sidewinder sleeps tonite").youtube(GEORDIE_BASSIST, "i-9aMVXPNvc").next().
                name("Orange crush").youtube(BRAND73, "gs2Wu1VSWWc").
                buildAll();
    }


    @Bean
    private List<Song> rhcp() {
        return Song.from(Author.RHCP).
                name("Californication").youtube(COVERSOLUTIONS, "vVSn1xindPM", of(HAMMER_ON, PULL_OFF)).next().
                name("By the way").youtube(COVERSOLUTIONS, "vVSn1xindPM", DADG, of(HAMMER_ON, PULL_OFF)).next().
                name("Under the bridge").youtube(COVERSOLUTIONS, "Tg-uUgEaovc", of(SLIDE)).next().
                name("Venice queen").youtube(LEO, "nOoaEqwQ6_8").next().
                name("Otherside").youtube(COVERSOLUTIONS, "XUTCU3v22GI").next().
                name("Dani California").youtube(COVERSOLUTIONS, "0HuEUVNU-Co").next().
                name("Can't stop").youtube(COVERSOLUTIONS, "nNszif3eDTs", of(SLAP)).next().
                name("Dark necessities").youtube(COVERSOLUTIONS, "FHohYlcdQkc", of(SLAP)).
                buildAll();
    }


    @Bean
    private List<Song> muse(){
        return Song.from(Author.MUSE).
                name("Psycho").youtube(COVERSOLUTIONS, "4CEzv6vZSiw", DADG, of(BEND)).next().
                name("Uprising").feel(SWING).youtube(TOM_BORNEMANN, "SRK76vhoIAA", of(BEND)).next().
                name("Reapers").youtube(TOM_BORNEMANN, "CGnx6vnGA8o", DADG, of(HAMMER_ON)).next().
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
        return Song.from(Author.TALKING_HEADS).name("Psycho killer").youtube(HARRY, "SNZHCz4rzKA").build();
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

}
