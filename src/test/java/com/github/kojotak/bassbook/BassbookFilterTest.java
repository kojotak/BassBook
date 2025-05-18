package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.junit.jupiter.api.Test;

import static com.github.kojotak.bassbook.data.Channel.COVERSOLUTIONS;
import static com.github.kojotak.bassbook.data.Channel.EUBASS;
import static com.github.kojotak.bassbook.data.Tuning.CGCF;
import static org.junit.jupiter.api.Assertions.*;

class BassbookFilterTest {

    private final Song toxicity = Song.from(Author.SOAD).name("Toxicity").meter(6, 8)
            .youtube(EUBASS, "fORp9OK7wys", CGCF)
            .youtube(COVERSOLUTIONS, "G_3Aze81cf0", CGCF)
            .build();


    @Test
    public void filterByEmptyFilterAcceptsAll(){
        var filter = new BassbookFilter();
        assertTrue(filter.test(toxicity));
    }

    @Test
    public void filterByNamePart(){
        var filter = new BassbookFilter();
        filter.setSongName("city");
        assertTrue(filter.test(toxicity));
    }

    @Test
    public void filterByNamePartTrimmed(){
        var filter = new BassbookFilter();
        filter.setSongName("  city  ");
        assertTrue(filter.test(toxicity));
    }

    @Test
    public void filterByNamePartCaseInsensitive(){
        var filter = new BassbookFilter();
        filter.setSongName("to");
        assertTrue(filter.test(toxicity));
    }

    @Test
    public void filterByOtherAuthor(){
        var filter = new BassbookFilter();
        filter.setAuthor(Author.MUSE);
        assertFalse(filter.test(toxicity));
    }

    @Test
    public void filterByAuthor(){
        var filter = new BassbookFilter();
        filter.setAuthor(Author.SOAD);
        assertTrue(filter.test(toxicity));
    }

    @Test
    public void filterByOtherTuning(){
        var filter = new BassbookFilter();
        filter.setTuning(Tuning.DADG);
        assertFalse(filter.test(toxicity));
    }

    @Test
    public void filterByChannel(){
        var filter = new BassbookFilter();
        filter.setChannel(COVERSOLUTIONS);
        assertTrue(filter.test(toxicity));
    }

    @Test
    public void filterByOtherTechnique(){
        var filter = new BassbookFilter();
        filter.setTechnique(Technique.SLAP);
        assertFalse(filter.test(toxicity));
    }

    @Test
    public void filterByMeter(){
        var filter = new BassbookFilter();
        filter.setMeter(new Meter(6,8));
        assertTrue(filter.test(toxicity));
    }

    @Test
    public void filterByMultipleCriteria(){
        var filter = new BassbookFilter();
        filter.setSongName("Toxic");
        filter.setMeter(new Meter(6,8));
        filter.setChannel(Channel.COVERSOLUTIONS);
        filter.setTuning(Tuning.CGCF);
        filter.setAuthor(Author.SOAD);
        assertTrue(filter.test(toxicity));
    }
}