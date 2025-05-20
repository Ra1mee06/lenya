package com.medcard.controllers;

import com.medcard.dao.MedicalEntryDAO;
import com.medcard.models.MedicalEntry;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddMedicalEntryServlet", value = "/doctor/addEntry")
public class AddMedicalEntryServlet extends HttpServlet {
    private final MedicalEntryDAO dao = new MedicalEntryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int recordId = Integer.parseInt(request.getParameter("recordId"));
            Date entryDate = Date.valueOf(request.getParameter("entryDate"));
            String doctorName = request.getParameter("doctorName");
            String diagnosis = request.getParameter("diagnosis");
            String treatment = request.getParameter("treatment");
            String notes = request.getParameter("notes");

            MedicalEntry entry = new MedicalEntry();
            entry.setRecordId(recordId);
            entry.setEntryDate(entryDate);
            entry.setDoctorName(doctorName);
            entry.setDiagnosis(diagnosis);
            entry.setTreatment(treatment);
            entry.setNotes(notes);

            boolean success = dao.addEntry(entry);
            request.setAttribute("message", success ? "Запись добавлена" : "Ошибка добавления");
        } catch (Exception e) {
            request.setAttribute("message", "Ошибка ввода данных: " + e.getMessage());
        }
        request.getRequestDispatcher("/doctor/dashboard.jsp").forward(request, response);
    }
}