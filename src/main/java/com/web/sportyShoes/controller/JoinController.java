package com.web.sportyShoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class JoinController {

    @GetMapping("/join")
    public String index() {
        return "auth/join";

    }

    @PostMapping("/join")
    public @ResponseBody String join() {
        return "layout";
    }


}
