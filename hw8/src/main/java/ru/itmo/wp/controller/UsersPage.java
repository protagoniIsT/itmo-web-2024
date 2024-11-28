package ru.itmo.wp.controller;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.Status;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String changeStatus(Status status,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/all";
        }

        if (checkUser(httpSession)) {
            return "redirect:/";
        }

        if (userService.findById(status.getUserId()) != null) {
            userService.updateStatus(status.getUserId(), status.getStatus());
            Object sessionUserId = httpSession.getAttribute("userId");
            if (sessionUserId != null && status.getUserId() == (Long) sessionUserId) {
                unsetUser(httpSession);
            }
             return "redirect:/users/all";
        } else {
            throw new RuntimeException("No such user");
        }
    }
}
