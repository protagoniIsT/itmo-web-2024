package ru.itmo.wp.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

//    @InitBinder("postForm")
//    public void initBinder(WebDataBinder webDataBinder){
//        webDataBinder.addValidators(registerValidator);
//    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable String id) {
        try {
            Long parsedId = Long.parseLong(id);
            return postService.findById(parsedId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @PostMapping("/posts/{login}")
    public void writePost(@RequestBody @Valid PostForm postForm, @PathVariable String login) {
        Post newPost = new Post();
        newPost.setTitle(postForm.getTitle());
        newPost.setText(postForm.getText());
        User user = userService.findUserByLogin(login);
        userService.writePost(user, newPost);
    }
}
