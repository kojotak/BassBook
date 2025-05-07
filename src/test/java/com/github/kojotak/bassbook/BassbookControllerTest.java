package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;

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

        var mv = controller.playDetail(author.getName(), "song", play.channel().id);
        assertModelAttributeValue(mv, "selectedAuthor", author);
        assertModelAttributeValue(mv, "selectedPlay", play);
    }

}