package com.medcard.controllers;

import com.medcard.dao.UserDAO;
import com.medcard.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

@WebServlet(name = "AddMedicalRecordServlet", value = "/admin/addRecord")
public class AddMedicalRecordServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/medical_records";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123qwe";

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int patientId = Integer.parseInt(request.getParameter("userId"));
            Date creationDate = Date.valueOf(request.getParameter("createdDate"));

            User user = userDAO.getUserById(patientId);
            if (user == null || !"PATIENT".equalsIgnoreCase(user.getRole())) {
                request.setAttribute("errorMessage", "Пациент с таким ID не найден");
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
                return;
            }

            String recordNumber = UUID.randomUUID().toString().substring(0, 12).toUpperCase();

            String sql = "INSERT INTO medical_records (patient_id, record_number, creation_date) VALUES (?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, patientId);
                stmt.setString(2, recordNumber);
                stmt.setDate(3, creationDate);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    request.setAttribute("successMessage", "Медкарта создана успешно");
                } else {
                    request.setAttribute("errorMessage", "Ошибка при создании медкарты");
                }
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка данных: " + e.getMessage());
        }
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}