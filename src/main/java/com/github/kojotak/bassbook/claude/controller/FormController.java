package com.github.kojotak.bassbook.claude.controller;

import com.github.kojotak.bassbook.claude.repository.YVideoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    private final YVideoRepository yVideoRepository;

    public FormController(YVideoRepository yVideoRepository) {
        this.yVideoRepository = yVideoRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videos", yVideoRepository.findAllOrderBySongName());
        return "index";
    }
}
