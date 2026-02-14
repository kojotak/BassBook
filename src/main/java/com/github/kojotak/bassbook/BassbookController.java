package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class BassbookController {

    private final Logger logger = LoggerFactory.getLogger(BassbookController.class);
    private final BassbookDatabase database;

    public BassbookController(BassbookDatabase database) {
        this.database = database;
    }

    @GetMapping("/")
    public ModelAndView home() {
        return createModelFromFilter(new BassbookFilter());
    }

    @PostMapping("/")
    public ModelAndView filter(BassbookFilter filter) {
        return createModelFromFilter(filter);
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

    @GetMapping("/author/{authorName}/song/{songName}/play/{channelId}/id/{youtubeId}")
    public ModelAndView playDetail(@PathVariable String authorName, @PathVariable String songName,
                                   @PathVariable String channelId, @PathVariable String youtubeId) {
        var model = new ModelAndView("index");
        var selectedSong = selectSong(authorName, songName, model);
        if (selectedSong != null) {
            var selectedPlay = selectedSong.plays().stream()
                    .filter(p -> p.channel().id.equals(channelId))
                    .filter( p ->p.id().equals(youtubeId) )
                    .findFirst().orElse(null);
            model.addObject("selectedPlay", selectedPlay);
        }
        return model;
    }

    @Nullable
    private Song selectSong(@Nullable String authorName, String songName, ModelAndView model) {
        var optionalAuthor = database.getAuthors().stream()
                .filter(a -> a.name().equals(authorName))
                .findFirst();
        var optionalSong = optionalAuthor.stream()
                .map(Author::songs).flatMap(List::stream)
                .filter(s -> s.getName().equals(songName))
                .findFirst();
        var selectedSong = optionalSong.orElse(null);
        model.addObject("selectedSong", selectedSong);
        model.addObject("selectedAuthor", optionalAuthor.map(Author::name).orElse(null));
        return selectedSong;
    }

    private ModelAndView createModelFromFilter(BassbookFilter filter){
        var authors = database.getAuthors();
        var allRows = authors.stream().flatMap( a -> a.songs().stream().map(s -> new Row(a, s, null))).toList();
        Comparator<Row> bySongName = Comparator.comparing( r -> r.song().name() );
        var filtered = allRows.stream().filter(filter).sorted(bySongName).toList();
        logger.info("filtered {} from {} songs using {}", filtered.size(), allRows.size(), filter);
        var model = new ModelAndView("index");
        model.addObject("filter", filter);
        model.addObject("rows", filtered);
        model.addObject("authors", authors.stream().map(Author::name).toList());
        model.addObject("meters", allRows.stream().map(Row::song).map(Song::meter).distinct().sorted().toList() );
        model.addObject("tunings", Stream.of(Tuning.values()).sorted(
                Comparator.comparing(Tuning::name)
        ).toList() );
        model.addObject("channels", Stream.of(Channel.values()).sorted(
                Comparator.comparing(ch -> ch.label.toLowerCase())
        ).toList() );
        model.addObject("technique", Stream.of(Technique.values()).sorted(
                Comparator.comparing(Technique::name)
        ).toList() );
        return model;
    }

}