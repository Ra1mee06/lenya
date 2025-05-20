package com.medcard.controllers;

import com.medcard.dao.UserDAO;
import com.medcard.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/login")
public class AuthServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.authenticate(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                switch (user.getRole()) {
    case "ADMIN":
        response.sendRedirect("admin/dashboard.jsp");
        break;
    case "PATIENT":
        response.sendRedirect("user/dashboard.jsp");
        break;
    case "DOCTOR":
        response.sendRedirect("doctor/dashboard.jsp");
        break;
    default:
        request.setAttribute("error", "Неизвестная роль пользователя");
        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        break;
}

            } else {
                request.setAttribute("error", "Неверный логин или пароль");
                request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Ошибка входа", e);
        }
    }
}