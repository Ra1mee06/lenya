package com.souvenirs.controllers;

import com.souvenirs.dao.DataInserter;
import com.souvenirs.dao.QueryExecutor;
import com.souvenirs.dao.DatabaseConnection;
import com.souvenirs.models.Manufacturer;
import com.souvenirs.models.Souvenir;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MainController", urlPatterns = {"/", "/search", "/add", "/process"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/":
                showHomePage(request, response);
                break;
            case "/search":
                showSearchPage(request, response);
                break;
            case "/add":
                showAddPage(request, response);
                break;
            default:
                showHomePage(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/process":
                    processRequest(request, response);
                    break;
                default:
                    showHomePage(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void showSearchPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }

    private void showAddPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Manufacturer> manufacturers = QueryExecutor.getAllManufacturers();
            request.setAttribute("manufacturers", manufacturers);
        } catch (SQLException e) {
            throw new ServletException("Ошибка при загрузке производителей", e);
        }
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        String queryType = request.getParameter("queryType");

        switch (queryType) {
            case "souvenirsByManufacturer":
                processSouvenirsByManufacturer(request, response);
                break;
            case "souvenirsByCountry":
                processSouvenirsByCountry(request, response);
                break;
            case "manufacturersWithPricesBelow":
                processManufacturersWithPricesBelow(request, response);
                break;
            case "manufacturersBySouvenirAndYear":
                processManufacturersBySouvenirAndYear(request, response);
                break;
            case "deleteManufacturer":
                processDeleteManufacturer(request, response);
                break;
            case "addData":
                processAddData(request, response);
                break;
            case "allManufacturers":
                processAllManufacturers(request, response);
                break;
            case "allSouvenirs":
                processAllSouvenirs(request, response);
                break;
            default:
                showHomePage(request, response);
        }
    }

    private void processSouvenirsByManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int manufacturerId = Integer.parseInt(request.getParameter("manufacturerId"));

        List<Souvenir> souvenirs = QueryExecutor.getSouvenirsByManufacturer(manufacturerId);

        request.setAttribute("resultType", "souvenirs");
        request.setAttribute("results", souvenirs);
        request.setAttribute("message", "Сувениры производителя с ID: " + manufacturerId);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void processSouvenirsByCountry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String country = request.getParameter("country");

        List<Souvenir> souvenirs = QueryExecutor.getSouvenirsByCountry(country);

        request.setAttribute("resultType", "souvenirs");
        request.setAttribute("results", souvenirs);
        request.setAttribute("message", "Сувениры из страны: " + country);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void processManufacturersWithPricesBelow(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        double price = Double.parseDouble(request.getParameter("price"));

        List<Manufacturer> manufacturers = QueryExecutor.getManufacturersWithPricesBelow(price);

        request.setAttribute("resultType", "manufacturers");
        request.setAttribute("results", manufacturers);
        request.setAttribute("message", "Производители с ценами ниже: " + price);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void processManufacturersBySouvenirAndYear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String souvenirName = request.getParameter("souvenirName");
        int year = Integer.parseInt(request.getParameter("year"));

        List<Manufacturer> manufacturers = QueryExecutor.getManufacturersBySouvenirAndYear(souvenirName, year);

        request.setAttribute("resultType", "manufacturers");
        request.setAttribute("results", manufacturers);
        request.setAttribute("message",
                String.format("Производители сувенира '%s' за %d год:", souvenirName, year));

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void processDeleteManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int manufacturerId = Integer.parseInt(request.getParameter("manufacturerId"));

        boolean success = DataInserter.deleteManufacturer(manufacturerId);

        request.setAttribute("message",
                success ? "Производитель и его сувениры успешно удалены!"
                        : "Ошибка при удалении производителя");

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void processAddData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        String dataType = request.getParameter("dataType");
        boolean success = false;
        String errorMessage = null;

        switch (dataType) {
            case "manufacturer":
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setName(request.getParameter("name"));
                manufacturer.setCountry(request.getParameter("country"));
                int manufacturerId = DataInserter.addManufacturer(manufacturer);
                success = manufacturerId != -1;
                break;
            case "souvenir":
                try {
                    int mId = Integer.parseInt(request.getParameter("manufacturerId"));

                    if (!manufacturerExists(mId)) {
                        errorMessage = "Производитель с ID " + mId + " не существует";
                        break;
                    }

                    Souvenir souvenir = new Souvenir();
                    souvenir.setName(request.getParameter("name"));
                    souvenir.setManufacturerId(mId);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date releaseDate = sdf.parse(request.getParameter("releaseDate"));
                    souvenir.setReleaseDate(releaseDate);

                    souvenir.setPrice(Double.parseDouble(request.getParameter("price")));
                    int souvenirId = DataInserter.addSouvenir(souvenir);
                    success = souvenirId != -1;
                } catch (NumberFormatException e) {
                    errorMessage = "Неверный формат ID производителя";
                }
                break;
        }

        if (errorMessage != null) {
            request.setAttribute("message", errorMessage);
        } else {
            request.setAttribute("message", success ? "Данные успешно добавлены!" : "Ошибка при добавлении данных");
        }
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private boolean manufacturerExists(int manufacturerId) throws SQLException {
        String query = "SELECT 1 FROM manufacturers WHERE manufacturer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, manufacturerId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void processAllManufacturers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Manufacturer> allManufacturers = QueryExecutor.getAllManufacturers();
        request.setAttribute("resultType", "manufacturers");
        request.setAttribute("results", allManufacturers);
        request.setAttribute("message", "Список всех производителей");
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void processAllSouvenirs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Souvenir> allSouvenirs = QueryExecutor.getAllSouvenirs();
        request.setAttribute("resultType", "souvenirs");
        request.setAttribute("results", allSouvenirs);
        request.setAttribute("message", "Список всех сувениров");
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
