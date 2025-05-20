package com.souvenirs.dao;

import com.souvenirs.models.Manufacturer;
import com.souvenirs.models.Souvenir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {

    public static List<Souvenir> getSouvenirsByManufacturer(int manufacturerId) throws SQLException {
        List<Souvenir> souvenirs = new ArrayList<>();
        String query = "SELECT * FROM souvenirs WHERE manufacturer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, manufacturerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    souvenirs.add(new Souvenir(
                            rs.getInt("souvenir_id"),
                            rs.getString("name"),
                            rs.getInt("manufacturer_id"),
                            rs.getDate("release_date"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return souvenirs;
    }

    public static List<Souvenir> getSouvenirsByCountry(String country) throws SQLException {
        List<Souvenir> souvenirs = new ArrayList<>();
        String query = "SELECT s.* FROM souvenirs s " +
                "JOIN manufacturers m ON s.manufacturer_id = m.manufacturer_id " +
                "WHERE m.country = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, country);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    souvenirs.add(new Souvenir(
                            rs.getInt("souvenir_id"),
                            rs.getString("name"),
                            rs.getInt("manufacturer_id"),
                            rs.getDate("release_date"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return souvenirs;
    }

    public static List<Manufacturer> getManufacturersWithPricesBelow(double price) throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = "SELECT DISTINCT m.* FROM manufacturers m " +
                "JOIN souvenirs s ON m.manufacturer_id = s.manufacturer_id " +
                "WHERE s.price < ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, price);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    manufacturers.add(new Manufacturer(
                            rs.getInt("manufacturer_id"),
                            rs.getString("name"),
                            rs.getString("country")
                    ));
                }
            }
        }
        return manufacturers;
    }

    public static List<Manufacturer> getManufacturersBySouvenirAndYear(String souvenirName, int year) throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = "SELECT m.* FROM manufacturers m " +
                "JOIN souvenirs s ON m.manufacturer_id = s.manufacturer_id " +
                "WHERE s.name = ? AND YEAR(s.release_date) = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, souvenirName);
            stmt.setInt(2, year);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    manufacturers.add(new Manufacturer(
                            rs.getInt("manufacturer_id"),
                            rs.getString("name"),
                            rs.getString("country")
                    ));
                }
            }
        }
        return manufacturers;
    }

    public static List<Manufacturer> getAllManufacturers() throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = "SELECT * FROM manufacturers ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                manufacturers.add(new Manufacturer(
                        rs.getInt("manufacturer_id"),
                        rs.getString("name"),
                        rs.getString("country")
                ));
            }
        }
        return manufacturers;
    }

    public static List<Souvenir> getAllSouvenirs() throws SQLException {
        List<Souvenir> souvenirs = new ArrayList<>();
        String query = "SELECT * FROM souvenirs ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                souvenirs.add(new Souvenir(
                        rs.getInt("souvenir_id"),
                        rs.getString("name"),
                        rs.getInt("manufacturer_id"),
                        rs.getDate("release_date"),
                        rs.getDouble("price")
                ));
            }
        }
        return souvenirs;
    }
}
