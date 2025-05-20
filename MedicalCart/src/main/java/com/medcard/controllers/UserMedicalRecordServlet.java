package com.medcard.controllers;

import com.medcard.dao.MedicalEntryDAO;
import com.medcard.dao.MedicalRecordDAO;
import com.medcard.models.MedicalEntry;
import com.medcard.models.MedicalRecord;
import com.medcard.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserMedicalRecordServlet", value = "/user/medical")
public class UserMedicalRecordServlet extends HttpServlet {
    private final MedicalRecordDAO recordDAO = new MedicalRecordDAO();
    private final MedicalEntryDAO entryDAO = new MedicalEntryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("../auth/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        MedicalRecord record = recordDAO.getByPatientId(user.getId());
        request.setAttribute("record", record);

        if (record != null) {
            List<MedicalEntry> entries = entryDAO.getEntriesByRecordId(record.getId());
            request.setAttribute("entries", entries);
        }

        request.getRequestDispatcher("/user/medical_record.jsp").forward(request, response);
    }
}
