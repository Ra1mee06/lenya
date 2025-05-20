package com.medcard.dao;

import com.medcard.models.MedicalEntry;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalEntryDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/medical_records";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123qwe";

    public List<MedicalEntry> getEntriesByRecordId(int recordId) {
        List<MedicalEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM medical_entries WHERE record_id = ? ORDER BY entry_date DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recordId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MedicalEntry entry = new MedicalEntry();
                    entry.setId(rs.getInt("id"));
                    entry.setRecordId(rs.getInt("record_id"));
                    entry.setEntryDate(rs.getDate("entry_date"));
                    entry.setDoctorName(rs.getString("doctor_name"));
                    entry.setDiagnosis(rs.getString("diagnosis"));
                    entry.setTreatment(rs.getString("treatment"));
                    entry.setNotes(rs.getString("notes"));
                    entries.add(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }
    public boolean addEntry(MedicalEntry entry) {
    String sql = "INSERT INTO medical_entries (record_id, entry_date, doctor_name, diagnosis, treatment, notes) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, entry.getRecordId());
        stmt.setDate(2, entry.getEntryDate());
        stmt.setString(3, entry.getDoctorName());
        stmt.setString(4, entry.getDiagnosis());
        stmt.setString(5, entry.getTreatment());
        stmt.setString(6, entry.getNotes());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}