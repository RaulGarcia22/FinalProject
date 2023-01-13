package controller;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    List<String> placesmax = new ArrayList<>();
    List<String> placesmin = new ArrayList<>();

    public void dataReaderMax(LocalDate newFrom, LocalDate newTo) throws SQLException {
        String url = "SQLitedatabase/database.db";
        String dbPath = "jdbc:sqlite:" + url;
        Connection con = DriverManager.getConnection(dbPath);
        Statement statement = con.createStatement();
        ResultSet resultmax = statement.executeQuery("SELECT place, date FROM Tempmax");
        String datemax;
        String placemax;
        while (resultmax.next()) {
            datemax = resultmax.getString("date");
            placemax = resultmax.getString("place");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate newDatemax = LocalDate.parse(datemax, format);
            if (newDatemax.isBefore(newTo)) {
                if (newDatemax.isAfter(newFrom)) {
                    placesmax.add(placemax);
                }
            }
        }
        con.close();
    }

    public void dataReaderMin(LocalDate newFrom, LocalDate newTo) throws SQLException {
        String url = "SQLitedatabase/database.db";
        String dbPath = "jdbc:sqlite:" + url;
        Connection con = DriverManager.getConnection(dbPath);
        Statement statement = con.createStatement();
        ResultSet resultmin = statement.executeQuery("SELECT place, date FROM Tempmin");
        String datemin;
        String placemin;
        while (resultmin.next()) {
            datemin = resultmin.getString("date");
            placemin = resultmin.getString("place");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate newDatemin = LocalDate.parse(datemin, format);
            if (newDatemin.isBefore(newTo)) {
                if (newDatemin.isAfter(newFrom)) {
                    placesmin.add(placemin);
                }
            }
        }
        con.close();
    }

    public List<String> getPlacesmax(LocalDate newFrom, LocalDate newTo) throws SQLException {
        dataReaderMax(newFrom, newTo);
        return placesmax;
    }

    public List<String> getPlacesmin(LocalDate newFrom, LocalDate newTo) throws SQLException {
        dataReaderMin(newFrom, newTo);
        return placesmin;
    }
}
