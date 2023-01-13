package controller;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static spark.Spark.get;

public class WebService {
    public void webService() {
        WebService webService = new WebService();
        get("/v1/places/with-Tempmax-temperature", webService::getPlacesMax);
        get("/v1/places/with-Tempmin-temperature", webService::getPlacesMin);
    }

    public String getPlacesMin(Request request, Response response) throws SQLException {
        response.header("content-type", "application/json");
        String from = request.queryParams("from");
        String to = request.queryParams("to");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newFrom = LocalDate.parse(from, format);
        LocalDate newTo = LocalDate.parse(to, format);
        List<String> locations = new DataReader().getPlacesmin(newFrom, newTo);
        return toJson(locations);
    }

    public String getPlacesMax(Request request, Response response) throws SQLException {
        response.header("content-type", "application/json");
        String from = request.queryParams("from");
        String to = request.queryParams("to");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newFrom = LocalDate.parse(from, format);
        LocalDate newTo = LocalDate.parse(to, format);
        List<String> locations = new DataReader().getPlacesmax(newFrom, newTo);
        return toJson(locations);
    }

    private static String toJson(List<String> locations) {
        return new Gson().toJson(locations);
    }
}
