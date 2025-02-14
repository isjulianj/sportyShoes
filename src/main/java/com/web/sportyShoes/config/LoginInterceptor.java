package com.web.sportyShoes.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        if (session != null) {
            if (session.getAttribute("user") != null) {
                System.out.println("User is logged in, allowing request to proceed");
                if (requestURI.equals("/login")) {
                    response.sendRedirect("/admin");
                    return false;
                }
                return true; // User is logged in, allow the request to proceed
            } else {
                System.out.println("Session exists but user attribute is null");
            }
        } else {
            System.out.println("Session is null");
        }

        if (requestURI.startsWith("/admin")) {
            response.sendRedirect("/login"); // Redirect to login page if not logged in
            return false;
        }

        return true; // Allow other requests to proceed
    }
}