package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.EventType;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@SuppressWarnings({"unused"})
public class LogoutPage extends Page {

    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        User user = (User) request.getSession().getAttribute("user");
        userService.saveEvent(user, EventType.LOGOUT);

        request.getSession().removeAttribute("user");
        setMessage("Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
