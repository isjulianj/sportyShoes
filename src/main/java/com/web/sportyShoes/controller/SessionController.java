package com.web.sportyShoes.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    // create a session and store the attribute
    @GetMapping("/create")
    public String createSession(HttpSession session) {
        session.setAttribute("username", "test@test.com");

        String sessionId = session.getId();
        return "Session created with ID: " + sessionId;
    }

    @GetMapping("/get")
    public String getSession(HttpSession session) {
        String username = (String)session.getAttribute("username");

        if (username == null) {
            return "No Session found!";
        }

        return "Session found with username: " + username;
    }

    @GetMapping("/invalidate")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "Session invalidated!";
    }

}
