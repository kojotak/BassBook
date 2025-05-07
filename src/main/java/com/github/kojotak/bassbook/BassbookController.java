package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.Author;
import com.github.kojotak.bassbook.data.Song;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BassbookController {

    private final BassbookDatabase database;

    public BassbookController(BassbookDatabase database) {
        this.database = database;
    }

    @GetMapping("/")
    public ModelAndView home() {
        var model = new ModelAndView("index");
        model.addObject("songs", database.getSongs());
        return model;
    }

    @GetMapping("/author/{authorName}/song/{songName}")
    public ModelAndView songDetail(@PathVariable String authorName, @PathVariable String songName) {
        var model = new ModelAndView("index");
        var selectedSong = selectSong(authorName, songName, model);
        if (selectedSong != null && selectedSong.plays().size() == 1) {
            model.addObject("selectedPlay", selectedSong.plays().iterator().next());
        }
        return model;
    }

    @GetMapping("/author/{authorName}/song/{songName}/play/{channelId}")
    public ModelAndView playDetail(@PathVariable String authorName, @PathVariable String songName, @PathVariable String channelId) {
        var model = new ModelAndView("index");
        var selectedSong = selectSong(authorName, songName, model);
        if (selectedSong != null) {
            var selectedPlay = selectedSong.plays().stream()
                    .filter(p -> p.channel().id.equals(channelId))
                    .findFirst().orElse(null);
            model.addObject("selectedPlay", selectedPlay);
        }
        return model;
    }

    @Nullable
    private Author selectAuthor(String authorName, ModelAndView model) {
        var selectedAuthor = database.getSongs().stream()
                .filter(s -> s.author().getName().equals(authorName))
                .map(Song::author)
                .findFirst().orElse(null);
        model.addObject("selectedAuthor", selectedAuthor);
        return selectedAuthor;
    }

    @Nullable
    private Song selectSong(@Nullable String authorName, String songName, ModelAndView model) {
        var optionalSong = database.getSongs().stream()
                .filter(s -> s.author().getName().equals(authorName))
                .filter(s -> s.getName().equals(songName))
                .findFirst();
        var selectedSong = optionalSong.orElse(null);
        model.addObject("selectedSong", selectedSong);
        model.addObject("selectedAuthor", optionalSong.map(Song::author).orElse(null));
        return selectedSong;
    }

}