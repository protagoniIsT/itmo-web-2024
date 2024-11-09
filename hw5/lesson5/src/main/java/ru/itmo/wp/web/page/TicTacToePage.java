package ru.itmo.wp.web.page;

import ru.itmo.wp.model.State;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {

    private void action(Map<String, Object> view, HttpServletRequest request) {
        view.put("state", getState(request));
    }

    public void onMove(Map<String, Object> view, HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        State state = getState(request);
        for (String paramName : params.keySet()) {
            if (paramName.startsWith("cell_")) {
                String cellCoordinates = paramName.substring("cell_".length());
                int row = cellCoordinates.charAt(0) - '0';
                int col = cellCoordinates.charAt(1) - '0';
                state.makeMove(row, col);
                break;
            }
        }
        action(view, request);
    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().setAttribute("state", new State());
        action(view, request);
    }

    private State getState(HttpServletRequest request) {
        if (request.getSession().getAttribute("state") == null) {
            request.getSession().setAttribute("state", new State());
        }
        return (State) request.getSession().getAttribute("state");
    }
}
