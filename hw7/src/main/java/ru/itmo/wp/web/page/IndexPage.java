package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/** @noinspection unused*/
public class IndexPage extends Page {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        long id = Long.parseLong(request.getParameter("userId"));
        view.put("user", userService.find(id));
    }

    protected void findArticles(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.findArticlesDesc());
    }
}
