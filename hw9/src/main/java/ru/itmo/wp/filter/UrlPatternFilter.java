package ru.itmo.wp.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UrlPatternFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String originalUri = request.getRequestURI();

        if (originalUri.contains("//")) {
            String cleanedUri = originalUri.replaceAll("/+", "/");
            if (!originalUri.equals(cleanedUri)) {
                response.sendRedirect(cleanedUri);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
