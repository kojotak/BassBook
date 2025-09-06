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
        var optionalSong = database.getSongs().stream()
                .filter(s -> s.author().getName().equals(authorName))
                .filter(s -> s.getName().equals(songName))
                .findFirst();
        var selectedSong = optionalSong.orElse(null);
        model.addObject("selectedSong", selectedSong);
        model.addObject("selectedAuthor", optionalSong.map(Song::author).orElse(null));
        return selectedSong;
    }

    private ModelAndView createModelFromFilter(BassbookFilter filter){
        var all = database.getSongs();
        var filtered = all.stream().filter(filter).toList();

        // Determine page size (limit per page), default to 25 if not provided or invalid
        int pageSize = (filter.getLimitPage() != null && filter.getLimitPage() > 0) ? filter.getLimitPage() : 25;

        // Compute total pages (at least 1 to simplify UI rendering)
        int totalPages = filtered.isEmpty() ? 1 : (int) Math.ceil((double) filtered.size() / pageSize);

        // Determine requested/current page index (0-based) and clamp to valid range
        int requestedPageIndex = filter.getSkipPages() != null ? Math.max(0, filter.getSkipPages()) : 0;
        int currentPageIndex = Math.min(requestedPageIndex, Math.max(0, totalPages - 1));

        // Slice items for the current page
        long offset = (long) currentPageIndex * pageSize;
        var songs = filtered.stream().skip(offset).limit(pageSize).toList();

        logger.info("filtered {} of {} songs using {} | page {}/{} (size={})",
                songs.size(), all.size(), filter, currentPageIndex + 1, totalPages, pageSize);

        var model = new ModelAndView("index");
        model.addObject("filter", filter);
        model.addObject("songs", songs);

        // Pagination model attributes
        model.addObject("currentPage", currentPageIndex + 1); // 1-based for display
        model.addObject("totalPages", totalPages);
        model.addObject("hasPrev", currentPageIndex > 0);
        model.addObject("hasNext", currentPageIndex + 1 < totalPages);
        model.addObject("prevPageIndex", Math.max(0, currentPageIndex - 1));
        model.addObject("nextPageIndex", Math.min(Math.max(0, totalPages - 1), currentPageIndex + 1));
        model.addObject("lastPageIndex", Math.max(0, totalPages - 1));
        model.addObject("pageSize", pageSize);

        // Static/filter options
        model.addObject("authors", Stream.of(Author.values()).sorted(Named.BY_NAME).toList());
        model.addObject("meters", all.stream().map(Song::meter).distinct().sorted().toList() );
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