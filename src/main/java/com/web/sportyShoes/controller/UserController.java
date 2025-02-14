package com.web.sportyShoes.controller;


import com.web.sportyShoes.dto.UserEditDto;
import com.web.sportyShoes.model.User;
import com.web.sportyShoes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService service){
        this.userService = service;
    }

    @GetMapping
    public String user(Model model) {
        model.addAttribute("users", userService.getALl());
        return "admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        UserEditDto userDto = new UserEditDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirst_name());
        userDto.setLastName(user.getLast_name());
        userDto.setEmail(user.getEmail());
        userDto.setSlug(user.getSlug());

        model.addAttribute("userDto", userDto);
        return "admin/edit-user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserEditDto userDto) {
        userDto.setId(id);
        userService.updateUser(userDto);
        return "redirect:/admin/users";
    }


}
