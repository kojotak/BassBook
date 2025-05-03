package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.springframework.stereotype.Service;

import java.util.Collection  ;
import java.util.List;

import static com.github.kojotak.bassbook.data.Channel.*;
import static com.github.kojotak.bassbook.data.Technique.*;
import static com.github.kojotak.bassbook.data.Tuning.DADG;
import static java.util.EnumSet.of;

@Service
public class BassbookDatabase {

    private final Collection<Author> authors;

    public BassbookDatabase() {
        authors = List.of(cranberries(), rem(), rhcp());
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    private static Author cranberries() {
        return new Author("The Cranberries",
                new Song("Zombie", new Youtube(COVERSOLUTIONS, "u9Zuoepny2Y")),
                new Song("Promises", new Youtube(ROBERTO_YANGUS, "a0VlH1DhdIY"))
        );
    }

    private static Author rem(){
        return new Author("R.E.M",
                new Song("Man on the Moon", new Youtube(BRAND73, "yJSJjzp8y7g")),
                new Song("What's the frequency, Kenneth?", new Youtube(BRAND73, "rHRnfD6nhw0")),
                new Song("Drive", new Youtube(CARLOS_CARLESI, "iIoUexEa5xg")),
                new Song("The sidewinder sleeps tonite", new Youtube(GEORDIE_BASSIST, "i-9aMVXPNvc")),
                new Song("Orange crush", new Youtube(BRAND73, "gs2Wu1VSWWc"))
        );
    }

    private static Author rhcp() {
        return new Author("Red hot chilli peppers",
                new Song("Californication", new Youtube(COVERSOLUTIONS, "vVSn1xindPM", of(HAMMER_ON, PULL_OFF))),
                new Song("By the way", new Youtube(COVERSOLUTIONS, "vVSn1xindPM", DADG, of(HAMMER_ON, PULL_OFF))),
                new Song("Under the bridge", new Youtube(COVERSOLUTIONS, "Tg-uUgEaovc", of(SLIDE))),
                new Song("Venice queen", new Youtube(LEO, "nOoaEqwQ6_8")),
                new Song("Otherside", new Youtube(COVERSOLUTIONS, "XUTCU3v22GI"))
        );
    }
}
