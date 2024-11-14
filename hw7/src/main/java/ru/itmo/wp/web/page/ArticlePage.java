package ru.itmo.wp.web.page;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class ArticlePage extends Page {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        if (getUser() == null) {
            setMessage( "Sign in to create an article.");
            throw new RedirectException("/index");
        }
    }

    private void create(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article = new Article();
        User user = (User) request.getSession().getAttribute("user");
        article.setUserId(user.getId());
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));

        articleService.validateArticle(article);
        articleService.create(article);

        setMessage("✓ Article was successfully created");
        throw new RedirectException("/index");
    }
}
