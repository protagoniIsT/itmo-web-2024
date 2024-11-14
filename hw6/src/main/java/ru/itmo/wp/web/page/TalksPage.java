package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused"})
public class TalksPage extends Page {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();
        if (user == null) {
            setMessage("Sign in to use chat.");
            throw new RedirectException("/index");
        }
        List<User> users = userService.findAll();
        view.put("users", users);
        HashMap<String, String> loginById = new HashMap<>();
        for (User usr : users) {
            loginById.put(String.valueOf(usr.getId()), usr.getLogin());
        }
        view.put("talks", talkService.findMessagesByUserId(user.getId()));
        view.put("loginById", loginById);
    }

    private void sendMessage(HttpServletRequest request, Map<String, Object> view) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Talk talk = new Talk(getUser().getId(),
                             Long.parseLong(request.getParameter("select")),
                             request.getParameter("text"));
        if (!talk.getText().trim().isEmpty()) {
            talkService.sendMessage(talk);
            setMessage("✓ Message sent");
        } else {
            setMessage("× Message shouldn't be empty");
        }
        throw new RedirectException("/talks");
    }
}
