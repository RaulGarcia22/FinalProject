package controller;

import model.Datalake;
import model.FileDatalake;
import model.WeatherSensor;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Controller implements Datalake, WeatherSensorInterface{


    public void controller() throws IOException, ParseException {
        List<Weather> events = dataCollect();
        writeData(events);
    }
    @Override
    public List<Weather> dataCollect() {
        return new WeatherSensor().dataCollect();
    }
    @Override
    public void writeData(List<Weather> wheater) throws IOException, ParseException {
        new FileDatalake().writeData(wheater);
    }
}
