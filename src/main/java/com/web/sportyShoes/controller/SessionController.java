package com.web.sportyShoes.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/get")
    public ResponseEntity<String> getSession(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(404).body("No Session found!");
        }

        return ResponseEntity.ok("Session found with username: " + username);
    }

    @GetMapping("/invalidate")
    public ResponseEntity<String> invalidateSession(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Session invalidated!");
    }
}