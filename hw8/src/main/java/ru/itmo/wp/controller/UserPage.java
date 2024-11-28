package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user/", "/user/{id}"})
    public String user(Model model, @PathVariable(required = false) String id) {
        try {
            Long userId = Long.parseLong(id);
            model.addAttribute("user", userService.findById(userId));
        } catch (NumberFormatException e) {
            model.addAttribute("user", null);
        }
        return "UserPage";
    }
}
