package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class Page {
    protected final UserService userService = new UserService();
    protected final TalkService talkService = new TalkService();
    private static HttpServletRequest currRequest;

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        currRequest = request;
        putUser(view);
        putMessage(view);
    }

    protected void action(HttpServletRequest request, Map<String, Object> view) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // No operations.
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
    }

    private void putUser(Map<String, Object> view) {
        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }
    }

    private void putMessage(Map<String, Object> view) {
        String message = (String) currRequest.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            currRequest.getSession().removeAttribute("message");
        }
    }

    protected void setMessage(String message) {
        currRequest.getSession().setAttribute("message", message);
    }

    protected void setUser(User user) {
        currRequest.getSession().setAttribute("user", user);
    }

    protected User getUser() {
        return (User) currRequest.getSession().getAttribute("user");
    }
}
