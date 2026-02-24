package com.github.kojotak.bassbook.claude.controller;

import com.github.kojotak.bassbook.claude.model.VideoMetadata;
import com.github.kojotak.bassbook.claude.service.YouTubeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final YouTubeService youTubeService;

    public HomeController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    @PostMapping("/submit")
    public String handleUrlSubmission(@RequestParam("url") String url, Model model) {
        model.addAttribute("url", url);

        try {
            var metadata = youTubeService.processUrl(url);
            model.addAttribute("metadata", metadata);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "result";
    }
}
