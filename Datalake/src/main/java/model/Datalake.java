package model;

import controller.Weather;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Datalake {
    void writeData(List<Weather> events) throws IOException, ParseException;
}
