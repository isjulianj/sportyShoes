package com.web.sportyShoes.controller;

import com.web.sportyShoes.model.User;
import com.web.sportyShoes.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            System.out.println("User already logged in");
            return "redirect:/admin";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = userService.authenticate(email, password);
        if (user != null) {
            session.setAttribute("user", user);  // Store user in session
            session.setAttribute("username", user.getEmail());  // Store username in session
            session.setAttribute("loggedIn", true);
            return "redirect:/admin";
        }
        return "redirect:/login?error";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}