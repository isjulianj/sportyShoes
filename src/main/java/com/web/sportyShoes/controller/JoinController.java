package com.web.sportyShoes.controller;

import com.web.sportyShoes.dto.UserRegistrationDto;
import com.web.sportyShoes.model.Password;
import com.web.sportyShoes.model.User;
import com.web.sportyShoes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/join")
public class JoinController {

    private final UserService userService;

    public JoinController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("registrationDto", new UserRegistrationDto());
        return "auth/join";

    }

    @PostMapping
    public String join(@ModelAttribute UserRegistrationDto registrationDto, @RequestBody MultiValueMap<String, String> formData) {

        System.out.println(registrationDto.getEmail());
        System.out.println(registrationDto.getFirstName());
        System.out.println(registrationDto.getLastName());
        System.out.println(registrationDto.getPassword());

        userService.registerUser(registrationDto);
        //TODO: Add a success message to the view.
        return "redirect:/success";
    }




}
