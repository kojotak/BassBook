package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.Author;
import com.github.kojotak.bassbook.data.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;

@Controller
public class BassbookController {

    @Autowired
    private BassbookDatabase database;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("authors", database.getAuthors());
        return "index";
    }

    @GetMapping("/author/{authorName}/song/{songName}")
    public String songDetail(@PathVariable String authorName, @PathVariable String songName, Model model) {
        var selectedAuthor = selectAuthor(authorName, model);
        model.addAttribute("selectedAuthor", selectedAuthor);
        if (selectedAuthor != null) {
            var selectedSong = selectSong(selectedAuthor, songName, model);
            if (selectedSong != null && selectedSong.plays().size() == 1) {
                model.addAttribute("selectedPlay", selectedSong.plays().iterator().next());
            }
        }
        return "index";
    }

    @GetMapping("/author/{authorName}/song/{songName}/play/{channelId}")
    public String playDetail(@PathVariable String authorName, @PathVariable String songName, @PathVariable String channelId, Model model) {
        var selectedAuthor = selectAuthor(authorName, model);
        if (selectedAuthor != null) {
            var selectedSong = selectSong(selectedAuthor, songName, model);
            if (selectedSong != null) {
                var selectedPlay = selectedSong.plays().stream()
                        .filter(p -> p.channel().id.equals(channelId))
                        .findFirst().orElse(null);
                model.addAttribute("selectedPlay", selectedPlay);
            }
        }
        return "index";
    }

    @Nullable
    private Author selectAuthor(String authorName, Model model) {
        var authors = database.getAuthors();
        var selectedAuthor = authors.stream()
                .filter(a -> a.name().equals(authorName))
                .findFirst().orElse(null);
        model.addAttribute("selectedAuthor", selectedAuthor);
        return selectedAuthor;
    }

    @Nullable
    private Song selectSong(@Nullable Author author, String songName, Model model) {
        var selectedSong = Optional.ofNullable(author)
                .map(Author::songs).stream()
                .flatMap(Collection::stream)
                .filter(s -> s.name().equals(songName))
                .findFirst().orElse(null);
        model.addAttribute("selectedSong", selectedSong);
        return selectedSong;
    }

}