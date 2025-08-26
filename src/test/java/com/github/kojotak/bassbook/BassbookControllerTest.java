package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.*;

@ExtendWith(MockitoExtension.class)
class BassbookControllerTest {

    @Mock
    private BassbookDatabase database;

    @InjectMocks
    private BassbookController controller;

    @Test
    public void homeContainsSongs() {
        var mv = controller.home();

        assertModelAttributeAvailable(mv, "songs");
        verify(database).getSongs();
    }

    @Test
    public void songDetailContainsSelectedAuthor() {
        var song = new Song("song", Author.MUSE, Meter.COMMON, Feel.STRAIGHT, List.of());
        when(database.getSongs()).thenReturn(List.of(song));

        var mv = controller.songDetail(Author.MUSE.getName(), "song");
        assertModelAttributeValue(mv, "selectedAuthor", Author.MUSE);
    }

    @Test
    public void songDetailContainsSelectedPlayIfItIsTheOnlyOne() {
        var play = new Youtube(Channel.BRAND73, "id");
        var author = Author.NINA_SIMONE;
        var song = new Song("song", author, Meter.COMMON, Feel.STRAIGHT, List.of(play));
        when(database.getSongs()).thenReturn(List.of(song));

        var mv = controller.songDetail(author.getName(), "song");
        assertModelAttributeValue(mv, "selectedAuthor", author);
        assertModelAttributeValue(mv, "selectedPlay", play);
    }

    @Test
    public void playDetailContainsSelectedPlay() {
        var play = new Youtube(Channel.BRAND73, "id");
        var author = Author.NINA_SIMONE;
        var song = new Song("song", author, Meter.COMMON, Feel.STRAIGHT, List.of(play));

        when(database.getSongs()).thenReturn(List.of(song));

        var mv = controller.playDetail(author.getName(), "song", play.channel().id, play.id());
        assertModelAttributeValue(mv, "selectedAuthor", author);
        assertModelAttributeValue(mv, "selectedPlay", play);
    }

    @Test
    public void filterSongs() {
        var author = Author.SOAD;
        var firstSong = new Song("first", author, Meter.COMMON, Feel.STRAIGHT, List.of(new Youtube(Channel.COVERSOLUTIONS, "id_1")));
        var secondSong = new Song("second", author, Meter.COMMON, Feel.STRAIGHT, List.of(new Youtube(Channel.BRAND73, "id_2")));
        when(database.getSongs()).thenReturn(List.of(firstSong, secondSong));

        var filter = new BassbookFilter();
        filter.setAuthor(author);
        filter.setChannel(Channel.COVERSOLUTIONS);

        var mv = controller.filter(filter, new Paginator());

        assertModelAttributeValue(mv, "songs", List.of(firstSong));
        assertModelAttributeAvailable(mv, "filter");
        verify(database).getSongs();
    }

    @Test
    public void filterContainsAllAuthors() {
        when(database.getSongs()).thenReturn(List.of());

        var mv = controller.home();

        var authors = assertAndReturnModelAttributeOfType(mv, "authors", List.class);
        Stream.of(Author.values()).forEach(author -> assertTrue(authors.contains(author)));
    }

    @Test
    public void filterContainsAllTunings() {
        when(database.getSongs()).thenReturn(List.of());

        var mv = controller.home();

        var tunings = assertAndReturnModelAttributeOfType(mv, "tunings", List.class);
        Stream.of(Tuning.values()).forEach(tuning -> assertTrue(tunings.contains(tuning)));
    }

    @Test
    public void filterContainsAllChannels() {
        when(database.getSongs()).thenReturn(List.of());

        var mv = controller.home();

        var channels = assertAndReturnModelAttributeOfType(mv, "channels", List.class);
        Stream.of(Channel.values()).forEach(channel -> assertTrue(channels.contains(channel)));
    }

    @Test
    public void filterContainsAllTechnique() {
        when(database.getSongs()).thenReturn(List.of());

        var mv = controller.home();

        var technique = assertAndReturnModelAttributeOfType(mv, "technique", List.class);
        Stream.of(Technique.values()).forEach(t -> assertTrue(technique.contains(t)) );
    }

}