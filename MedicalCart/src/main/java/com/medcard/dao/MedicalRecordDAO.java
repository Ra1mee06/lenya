package com.medcard.dao;

import com.medcard.models.MedicalRecord;
import java.sql.*;

public class MedicalRecordDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/medical_records";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123qwe";

    public MedicalRecord getByPatientId(int patientId) {
        String sql = "SELECT * FROM medical_records WHERE patient_id = ? ORDER BY creation_date DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MedicalRecord record = new MedicalRecord();
                    record.setId(rs.getInt("id"));
                    record.setPatientId(rs.getInt("patient_id"));
                    record.setRecordNumber(rs.getString("record_number"));
                    record.setCreationDate(rs.getDate("creation_date"));
                    return record;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}