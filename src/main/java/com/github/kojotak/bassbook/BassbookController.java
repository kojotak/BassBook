package com.github.kojotak.bassbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        var authors = database.getAuthors();
        var selectedAuthor = authors.stream()
                .filter(a -> a.name().equals(authorName))
                .findFirst().orElse(null);
        model.addAttribute("selectedAuthor", selectedAuthor);
        if(selectedAuthor!=null){
            var selectedSong = selectedAuthor.songs().stream()
                    .filter(s -> s.name().equals(songName))
                    .findFirst().orElse(null);
            model.addAttribute("selectedSong", selectedSong);
            if(selectedSong!=null && selectedSong.plays().size()==1){
                model.addAttribute("selectedPlay", selectedSong.plays().iterator().next());
            }
        }
        return "index";
    }

    @GetMapping("/author/{authorName}/song/{songName}/play/{channelId}")
    public String playDetail(@PathVariable String authorName, @PathVariable String songName, @PathVariable String channelId, Model model) {
        var authors = database.getAuthors();
        var selectedAuthor = authors.stream()
                .filter(a -> a.name().equals(authorName))
                .findFirst().orElse(null);
        model.addAttribute("selectedAuthor", selectedAuthor);
        if(selectedAuthor!=null){
            var selectedSong = selectedAuthor.songs().stream()
                    .filter(s -> s.name().equals(songName))
                    .findFirst().orElse(null);
            model.addAttribute("selectedSong", selectedSong);
            if(selectedSong!=null){
                var selectedPlay = selectedSong.plays().stream()
                        .filter( p -> p.channel().id.equals(channelId))
                        .findFirst().orElse(null);
                model.addAttribute("selectedPlay", selectedPlay);
            }
        }
        return "index";
    }

}