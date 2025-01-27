package com.web.sportyShoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public @ResponseBody String hello(@RequestParam(name="name", required = false, defaultValue = "Sporty Shoes") String name) {

        return "Welcome to " + name;
    }
}
