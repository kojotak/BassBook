package com.github.kojotak.bassbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BassbookController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private BassbookDatabase database;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("applicationName", applicationName);
        model.addAttribute("authors", database.getAuthors());
        return "index";
    }
}