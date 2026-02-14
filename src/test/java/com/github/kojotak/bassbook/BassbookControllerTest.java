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

        assertModelAttributeAvailable(mv, "rows");
        verify(database).getAuthors();
    }

    @Test
    public void songDetailContainsSelectedAuthor() {
        var author = new Author("Muse", List.of(new Song("song", Meter.COMMON, Feel.STRAIGHT, List.of())));
        when(database.getAuthors()).thenReturn(List.of(author));

        var mv = controller.songDetail("Muse", "song");
        assertModelAttributeValue(mv, "selectedAuthor", "Muse");
    }

    @Test
    public void songDetailContainsSelectedPlayIfItIsTheOnlyOne() {
        var play = new Youtube(Channel.BRAND73, "id");
        var song = new Song("song", Meter.COMMON, Feel.STRAIGHT, List.of(play));
        var author = new Author("Nina Simone", List.of(song));
        when(database.getAuthors()).thenReturn(List.of(author));

        var mv = controller.songDetail(author.name(), "song");
        assertModelAttributeValue(mv, "selectedAuthor", author.name());
        assertModelAttributeValue(mv, "selectedPlay", play);
    }

    @Test
    public void playDetailContainsSelectedPlay() {
        var play = new Youtube(Channel.BRAND73, "id");
        var song = new Song("song", Meter.COMMON, Feel.STRAIGHT, List.of(play));
        var author = new Author("Nina Simone", List.of(song));

        when(database.getAuthors()).thenReturn(List.of(author));

        var mv = controller.playDetail(author.name(), "song", play.channel().id, play.id());
        assertModelAttributeValue(mv, "selectedAuthor", author.name());
        assertModelAttributeValue(mv, "selectedPlay", play);
    }

    @Test
    public void filterSongs() {
        var firstSong = new Song("first", Meter.COMMON, Feel.STRAIGHT, List.of(new Youtube(Channel.COVERSOLUTIONS, "id_1")));
        var secondSong = new Song("second", Meter.COMMON, Feel.STRAIGHT, List.of(new Youtube(Channel.BRAND73, "id_2")));
        var author = new Author("System of the Dawn", List.of(firstSong, secondSong));
        when(database.getAuthors()).thenReturn(List.of(author));

        var filter = new BassbookFilter();
        filter.setAuthorName(author.name());
        filter.setChannel(Channel.COVERSOLUTIONS);

        var mv = controller.filter(filter);

        assertModelAttributeValue(mv, "rows", List.of(new Row(author, firstSong, null)));
        assertModelAttributeAvailable(mv, "filter");
        verify(database).getAuthors();
    }

    @Test
    public void filterContainsAllTunings() {
        when(database.getAuthors()).thenReturn(List.of());

        var mv = controller.home();

        var tunings = assertAndReturnModelAttributeOfType(mv, "tunings", List.class);
        Stream.of(Tuning.values()).forEach(tuning -> assertTrue(tunings.contains(tuning)));
    }

    @Test
    public void filterContainsAllChannels() {
        when(database.getAuthors()).thenReturn(List.of());

        var mv = controller.home();

        var channels = assertAndReturnModelAttributeOfType(mv, "channels", List.class);
        Stream.of(Channel.values()).forEach(channel -> assertTrue(channels.contains(channel)));
    }

    @Test
    public void filterContainsAllTechnique() {
        when(database.getAuthors()).thenReturn(List.of());

        var mv = controller.home();

        var technique = assertAndReturnModelAttributeOfType(mv, "technique", List.class);
        Stream.of(Technique.values()).forEach(t -> assertTrue(technique.contains(t)) );
    }

}