package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.Author;
import com.github.kojotak.bassbook.data.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

@Controller
public class BassbookController {

    @Autowired
    private BassbookDatabase database;

    @GetMapping("/")
    public ModelAndView home() {
        var model = new ModelAndView("index");
        model.addObject("authors", database.getAuthors());
        return model;
    }

    @GetMapping("/author/{authorName}/song/{songName}")
    public ModelAndView songDetail(@PathVariable String authorName, @PathVariable String songName) {
        var model = new ModelAndView("index");
        var selectedAuthor = selectAuthor(authorName, model);
        model.addObject("selectedAuthor", selectedAuthor);
        if (selectedAuthor != null) {
            var selectedSong = selectSong(selectedAuthor, songName, model);
            if (selectedSong != null && selectedSong.plays().size() == 1) {
                model.addObject("selectedPlay", selectedSong.plays().iterator().next());
            }
        }
        return model;
    }

    @GetMapping("/author/{authorName}/song/{songName}/play/{channelId}")
    public ModelAndView playDetail(@PathVariable String authorName, @PathVariable String songName, @PathVariable String channelId) {
        var model = new ModelAndView("index");
        var selectedAuthor = selectAuthor(authorName, model);
        if (selectedAuthor != null) {
            var selectedSong = selectSong(selectedAuthor, songName, model);
            if (selectedSong != null) {
                var selectedPlay = selectedSong.plays().stream()
                        .filter(p -> p.channel().id.equals(channelId))
                        .findFirst().orElse(null);
                model.addObject("selectedPlay", selectedPlay);
            }
        }
        return model;
    }

    @Nullable
    private Author selectAuthor(String authorName, ModelAndView model) {
        var authors = database.getAuthors();
        var selectedAuthor = authors.stream()
                .filter(a -> a.name().equals(authorName))
                .findFirst().orElse(null);
        model.addObject("selectedAuthor", selectedAuthor);
        return selectedAuthor;
    }

    @Nullable
    private Song selectSong(@Nullable Author author, String songName, ModelAndView model) {
        var selectedSong = Optional.ofNullable(author)
                .map(Author::songs).stream()
                .flatMap(Collection::stream)
                .filter(s -> s.name().equals(songName))
                .findFirst().orElse(null);
        model.addObject("selectedSong", selectedSong);
        return selectedSong;
    }

}