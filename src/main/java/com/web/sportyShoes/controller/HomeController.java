package com.web.sportyShoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Sporty Shoes");
        model.addAttribute("metaDescription", "Discover our collection of sporty shoes designed for performance.");
        model.addAttribute("metaKeywords", "sporty shoes, sneakers, performance, style");


        return "home";
    }

}