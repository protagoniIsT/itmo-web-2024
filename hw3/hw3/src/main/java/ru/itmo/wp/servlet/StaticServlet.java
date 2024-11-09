package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        String[] srcPaths = uri.split("\\+");

        boolean isMimeTypeSet = false;

        try (OutputStream outputStream = response.getOutputStream()) {

            for (String srcUri : srcPaths) {
                String srcFilePath = Paths.get(System.getProperty("user.dir"), "src", "main", "webapp", "static", srcUri).toString();
                File file = new File(srcFilePath);
                if (!file.exists()) {
                    file = new File(getServletContext().getRealPath("/static/" + srcUri));
                }
                if (file.isFile()) {
                    if (!isMimeTypeSet) {
                        response.setContentType(getServletContext().getMimeType(file.getName()));
                        isMimeTypeSet = true;
                    }
                    Files.copy(file.toPath(), outputStream);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            }
        }
    }
}