package controller;

import model.Database;
import model.Datamart;
import model.FileDatamart;
import model.ReaderInterface;
import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller implements Datamart, ReaderInterface {
    public void controller() throws IOException, SQLException {
        insertMin(init(), getMin());
        insertMax(init(), getMax());
    }


    public JSONArray getMax() throws IOException {
        return new FileDatamart().getMax();
    }

    public JSONArray getMin() throws IOException {
        return new FileDatamart().getMin();
    }

    @Override
    public Statement init() throws SQLException {
        return new Database().init();
    }

    @Override
    public void insertMax(Statement statement, JSONArray maximos) throws SQLException {
        new Database().insertMax(statement, maximos);
    }

    @Override
    public void insertMin(Statement statement, JSONArray minimos) throws SQLException {
        new Database().insertMin(statement, minimos);
    }
}
