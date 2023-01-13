package model;

import org.json.JSONArray;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database implements Datamart {

    public Statement init() throws SQLException {
        String dbPath = "C:/Users/Usuario/IdeaProjects/FinalProject/SQLitedatabase/database.db";
        Connection connection = connect(dbPath);
        Statement statement = connection.createStatement();
        createTable(statement);
        return statement;
    }

    private Connection connect(String dbPath) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Tempmin (" +
                "date TEXT," +
                "station TEXT," +
                "place TEXT," +
                "time TEXT," +
                "value REAL" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS Tempmax (" +
                "date TEXT," +
                "station TEXT," +
                "place TEXT," +
                "time TEXT," +
                "value REAL" +
                ");");
    }

    public void insertMax(Statement statement, JSONArray maximos) throws SQLException {
        statement.execute("DELETE FROM Tempmax;");
        for (Object obj : maximos) {
            statement.execute(Translator.getMax(obj));
        }
    }

    public void insertMin(Statement statement, JSONArray minimos) throws SQLException {
        statement.execute("DELETE FROM Tempmin;");
        for (Object obj : minimos) {
            statement.execute(Translator.getMin(obj));
        }
    }
}
