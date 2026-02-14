package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.kojotak.bassbook.data.Channel.COVERSOLUTIONS;
import static com.github.kojotak.bassbook.data.Channel.EUBASS;
import static com.github.kojotak.bassbook.data.Tuning.CGCF;
import static org.junit.jupiter.api.Assertions.*;

class BassbookFilterTest {

    private final Song toxicity = Song.name("Toxicity").meter(6, 8)
            .youtubeAnd(EUBASS, "fORp9OK7wys", CGCF)
            .youtube(COVERSOLUTIONS, "G_3Aze81cf0", CGCF);
    private final Row row = new Row(new Author("System of a Down", List.of(toxicity)), toxicity, Tag.PLAY);


    @Test
    public void filterByEmptyFilterAcceptsAll(){
        var filter = new BassbookFilter();
        assertTrue(filter.test(row));
    }

    @Test
    public void filterByNamePart(){
        var filter = new BassbookFilter();
        filter.setSongName("city");
        assertTrue(filter.test(row));
    }

    @Test
    public void filterByNamePartTrimmed(){
        var filter = new BassbookFilter();
        filter.setSongName("  city  ");
        assertTrue(filter.test(row));
    }

    @Test
    public void filterByNamePartCaseInsensitive(){
        var filter = new BassbookFilter();
        filter.setSongName("to");
        assertTrue(filter.test(row));
    }

    @Test
    public void filterByOtherAuthor(){
        var filter = new BassbookFilter();
        filter.setAuthorName(AuthorEnum.MUSE.getName());
        assertFalse(filter.test(row));
    }

    @Test
    public void filterByAuthor(){
        var filter = new BassbookFilter();
        filter.setAuthorName(AuthorEnum.SOAD.getName());
        assertTrue(filter.test(row));
    }

    @Test
    public void filterByOtherTuning(){
        var filter = new BassbookFilter();
        filter.setTuning(Tuning.DADG);
        assertFalse(filter.test(row));
    }

    @Test
    public void filterByChannel(){
        var filter = new BassbookFilter();
        filter.setChannel(COVERSOLUTIONS);
        assertTrue(filter.test(row));
    }

    @Test
    public void filterByOtherTechnique(){
        var filter = new BassbookFilter();
        filter.setTechnique(Technique.SLAP);
        assertFalse(filter.test(row));
    }

    @Test
    public void filterByMeter(){
        var filter = new BassbookFilter();
        filter.setMeter(new Meter(6,8));
        assertTrue(filter.test(row));
    }

    @Test
    public void filterByMultipleCriteria(){
        var filter = new BassbookFilter();
        filter.setSongName("Toxic");
        filter.setMeter(new Meter(6,8));
        filter.setChannel(Channel.COVERSOLUTIONS);
        filter.setTuning(Tuning.CGCF);
        filter.setAuthorName(AuthorEnum.SOAD.getName());
        assertTrue(filter.test(row));
    }
}