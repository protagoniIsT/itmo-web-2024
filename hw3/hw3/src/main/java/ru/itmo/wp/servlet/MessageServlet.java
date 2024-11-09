package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MessageServlet extends HttpServlet {

    private static final ArrayList<Map<String, String>> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] splitUri = uri.split("/");
        String postRequest = splitUri[splitUri.length - 1];
        switch (postRequest) {
            case ("auth"):
                auth(request, response);
                break;
            case ("findAll"):
                findAll(response);
                break;
            case ("add"):
                add(request);
                break;
        }
    }

    private void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String currUsername = request.getParameter("user") == null ? "" : request.getParameter("user");
        session.setAttribute("user", currUsername);
        String json = new Gson().toJson(currUsername);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    private void findAll(HttpServletResponse response) throws IOException {
        String json = new Gson().toJson(messages);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    private void add(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        String text = request.getParameter("text");
        String username = (session != null) ? (String) session.getAttribute("user") : null;
        Map<String, String> message = new LinkedHashMap<>();
        message.put("user", username);
        message.put("text", text);
        messages.add(message);
    }
}
