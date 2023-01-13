package model;

import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public interface Datamart {
    Statement init() throws SQLException;

    void insertMax(Statement statement, JSONArray maximos) throws SQLException;

    void insertMin(Statement statement, JSONArray minimos) throws SQLException;
}
