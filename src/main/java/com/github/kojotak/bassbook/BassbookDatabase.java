package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection  ;
import java.util.Comparator;

import static com.github.kojotak.bassbook.data.Channel.*;
import static com.github.kojotak.bassbook.data.Feel.SWING;
import static com.github.kojotak.bassbook.data.Technique.*;
import static com.github.kojotak.bassbook.data.Tuning.DADG;
import static java.util.EnumSet.of;

@Service
public class BassbookDatabase implements ApplicationListener<ContextRefreshedEvent> {

    private final Collection<Author> authors = new ArrayList<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        authors.addAll(event.getApplicationContext()
                .getBeansOfType(Author.class)
                .values().stream()
                .sorted(Comparator.comparing(Author::name))
                .toList());
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    @Bean
    private Author cranberries() {
        return new Author("The Cranberries",
                new Song("Zombie", new Youtube(COVERSOLUTIONS, "u9Zuoepny2Y")),
                new Song("Promises", new Youtube(ROBERTO_YANGUS, "a0VlH1DhdIY")),
                new Song("Linger", new Youtube(COVERSOLUTIONS, "T1JCwqngEl0"))
        );
    }

    @Bean
    private Author rem(){
        return new Author("R.E.M",
                new Song("Man on the Moon", new Youtube(BRAND73, "yJSJjzp8y7g")),
                new Song("What's the frequency, Kenneth?", new Youtube(BRAND73, "rHRnfD6nhw0")),
                new Song("Drive", new Youtube(CARLOS_CARLESI, "iIoUexEa5xg")),
                new Song("The sidewinder sleeps tonite", new Youtube(GEORDIE_BASSIST, "i-9aMVXPNvc")),
                new Song("Orange crush", new Youtube(BRAND73, "gs2Wu1VSWWc"))
        );
    }

    @Bean
    private Author rhcp() {
        return new Author("Red hot chilli peppers",
                new Song("Californication", new Youtube(COVERSOLUTIONS, "vVSn1xindPM", of(HAMMER_ON, PULL_OFF))),
                new Song("By the way", new Youtube(COVERSOLUTIONS, "vVSn1xindPM", DADG, of(HAMMER_ON, PULL_OFF))),
                new Song("Under the bridge", new Youtube(COVERSOLUTIONS, "Tg-uUgEaovc", of(SLIDE))),
                new Song("Venice queen", new Youtube(LEO, "nOoaEqwQ6_8")),
                new Song("Otherside", new Youtube(COVERSOLUTIONS, "XUTCU3v22GI")),
                new Song("Dani California", new Youtube(COVERSOLUTIONS, "0HuEUVNU-Co")),
                new Song("Can't stop", new Youtube(COVERSOLUTIONS, "nNszif3eDTs", of(SLAP))),
                new Song("Dark necessities", new Youtube(COVERSOLUTIONS, "FHohYlcdQkc", of(SLAP)))
        );
    }

    @Bean
    private Author muse(){
        return new Author("Muse",
                new Song("Psycho", new Youtube(COVERSOLUTIONS, "4CEzv6vZSiw", DADG, of(BEND))),
                new Song("Uprising", SWING, new Youtube(TOM_BORNEMANN, "SRK76vhoIAA", of(BEND))),
                new Song("Reapers", new Youtube(TOM_BORNEMANN, "CGnx6vnGA8o", DADG, of(HAMMER_ON))),
                new Song("Hysteria",
                        new Youtube(NATE_NAVARRO, "2-BidwjmCgc"),
                        new Youtube(COVERSOLUTIONS, "QW0qlOSdkrM")
                )
                );
    }

    @Bean
    private Author philCollins(){
        return new Author("Phil Collins", new Song("Another day in paradise",
                        new Youtube(NOCCO_CAT, "bZ3CSRQ6e4U", of(SLIDE)),
                        new Youtube(TOM_BORNEMANN, "BdpPpmMnjAo", of(SLIDE))
                ));
    }

    @Bean
    private Author ninaSimone() {
        return new Author("Nina Simone", new Song("Feeling good", new Youtube(HARRY, "qs9KVyJnKIU")));
    }

    @Bean
    private Author rayCharles() {
        return new Author("Ray Charles", new Song("Hit the road, Jack", new Youtube(HARRY, "M9NNOtMgAhk")));
    }

    @Bean
    private Author billyIdol() {
        return new Author("Billy Idol", new Song("White wedding", new Youtube(HARRY, "TqA_IWPuGcU")));
    }

    @Bean
    private Author talkingHeads() {
        return new Author("Talking heads", new Song("Psycho killer", new Youtube(HARRY, "SNZHCz4rzKA")));
    }

    @Bean
    private Author whiteStripes() {
        return new Author("The White stripes", new Song("Seven nation army", new Youtube(HARRY, "6_7VrkrUuKc")));
    }

    @Bean
    private Author blackEyedPeas() {
        return new Author("The Black eyed peas", new Song("Let's get it started", new Youtube(HARRY, "iz89AiRQhE0")));
    }
}
