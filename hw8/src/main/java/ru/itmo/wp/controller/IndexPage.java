package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexPage extends Page {

    @GetMapping({"", "/"})
    public String index() {
        return "IndexPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetUser(httpSession);
        setMessage(httpSession, "Good bye!");
        return "redirect:";
    }
}
