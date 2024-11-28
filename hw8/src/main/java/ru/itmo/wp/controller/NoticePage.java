package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.Text;
import ru.itmo.wp.form.validator.NoticeValidator;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {
    private final NoticeService noticeService;
    private final NoticeValidator noticeValidator;

    public NoticePage(NoticeService noticeService, NoticeValidator noticeValidator) {
        this.noticeService = noticeService;
        this.noticeValidator = noticeValidator;
    }

    @InitBinder("noticeText")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(noticeValidator);
    }

    @GetMapping("/notice")
    public String noticeGet(Model model) {
        model.addAttribute("noticeText", new Text());
        return "NoticePage";
    }

    @PostMapping("/notice")
    public String noticePost(@Valid @ModelAttribute("noticeText") Text noticeText,
                             BindingResult bindingResult,
                             HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }

        noticeService.add(noticeText);
        setMessage(httpSession, "Notice has been created!");

        return "redirect:/";
    }
}
