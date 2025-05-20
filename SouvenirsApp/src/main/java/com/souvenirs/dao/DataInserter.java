package com.souvenirs.dao;

import com.souvenirs.models.Manufacturer;
import com.souvenirs.models.Souvenir;
import java.sql.*;

public class DataInserter {
    public static int addManufacturer(Manufacturer manufacturer) throws SQLException {
        String query = "INSERT INTO manufacturers (name, country) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, manufacturer.getName());
            stmt.setString(2, manufacturer.getCountry());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    public static int addSouvenir(Souvenir souvenir) throws SQLException {
        String query = "INSERT INTO souvenirs (name, manufacturer_id, release_date, price) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, souvenir.getName());
            stmt.setInt(2, souvenir.getManufacturerId());
            stmt.setDate(3, new java.sql.Date(souvenir.getReleaseDate().getTime()));
            stmt.setDouble(4, souvenir.getPrice());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    public static boolean deleteManufacturer(int manufacturerId) throws SQLException {
        String query = "DELETE FROM manufacturers WHERE manufacturer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, manufacturerId);
            return stmt.executeUpdate() > 0;
        }
    }
}