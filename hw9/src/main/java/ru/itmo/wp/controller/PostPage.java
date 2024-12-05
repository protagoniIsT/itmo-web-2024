package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final CommentService commentService;

    public PostPage(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping({"/post/", "/post/{id}"})
    public String post(Model model, @PathVariable(required = false) String id) {
        try {
            Long postId = Long.parseLong(id);
            model.addAttribute("post", postService.findById(postId));
            if (!model.containsAttribute("comment")) {
                model.addAttribute("comment", new Comment());
            }
        } catch (NumberFormatException e) {
            model.addAttribute("post", null);
        }
        return "PostPage";
    }

    @PostMapping({"/post/{id}"})
    public String writeComment(
            @PathVariable String id,
            @Valid @ModelAttribute("comment") Comment comment,
            BindingResult bindingResult,
            Model model,
            HttpSession httpSession
    ) {
        Post post = postService.findById(Long.parseLong(id));
        model.addAttribute("post", post);

        if (bindingResult.hasErrors()) {
            return "PostPage";
        }

        comment.setUser((User) model.getAttribute("user"));
        comment.setPost(post);
        commentService.saveComment(comment);

        putMessage(httpSession, "Comment was successfully created");
        return "redirect:/post/" + id;
    }

}
