package com.medcard.controllers;

import com.medcard.dao.UserDAO;
import com.medcard.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/addUser")
public class AddUserServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String fullName = request.getParameter("fullName");
            Date birthDate = Date.valueOf(request.getParameter("birthDate"));
            String insuranceNumber = request.getParameter("insuranceNumber");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            user.setFullName(fullName);
            user.setBirthDate(birthDate);
            user.setInsuranceNumber(insuranceNumber);

            boolean created = userDAO.addUser(user);
            if (created) {
                request.setAttribute("successMessage", "Пользователь добавлен успешно");
            } else {
                request.setAttribute("errorMessage", "Ошибка при добавлении пользователя");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка данных: " + e.getMessage());
        }
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}
