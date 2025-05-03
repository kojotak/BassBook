package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.Author;
import com.github.kojotak.bassbook.data.Channel;
import com.github.kojotak.bassbook.data.Song;
import com.github.kojotak.bassbook.data.Youtube;
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
    public void homeContainsAuthors() {
        var mv = controller.home();

        assertModelAttributeAvailable(mv, "authors");
        verify(database).getAuthors();
    }

    @Test
    public void songDetailContainsSelectedAuthor() {
        var author = new Author("author");
        when(database.getAuthors()).thenReturn(List.of(author));

        var mv = controller.songDetail("author", "song");
        assertModelAttributeValue(mv, "selectedAuthor", author);
    }

    @Test
    public void songDetailContainsSelectedPlayIfItIsTheOnlyOne() {
        var play = new Youtube(Channel.BRAND73, "id");
        var song = new Song("song", play);
        var author = new Author("author", song);
        when(database.getAuthors()).thenReturn(List.of(author));

        var mv = controller.songDetail("author", "song");
        assertModelAttributeValue(mv, "selectedAuthor", author);
        assertModelAttributeValue(mv, "selectedPlay", play);
    }

    @Test
    public void playDetailContainsSelectedPlay() {
        var play = new Youtube(Channel.BRAND73, "id");
        var song = new Song("song", play);
        var author = new Author("author", song);
        when(database.getAuthors()).thenReturn(List.of(author));

        var mv = controller.playDetail("author", "song", play.channel().id);
        assertModelAttributeValue(mv, "selectedAuthor", author);
        assertModelAttributeValue(mv, "selectedPlay", play);
    }


}