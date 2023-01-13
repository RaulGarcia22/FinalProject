package model;

import controller.Weather;
import controller.WeatherCollect;
import controller.WeatherSensorInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherSensor implements WeatherSensorInterface {
    JSONArray filteredArray = new WeatherCollect().getFilteredArray();
    List<Weather> WeatherList = new ArrayList<>();

    public List<Weather> dataCollect() {
        for (Object obj : filteredArray) {
            if (obj instanceof JSONObject) {
                JSONObject jsonObj = (JSONObject) obj;
                Double tamin = jsonObj.optDouble("tamin");
                Double tamax = jsonObj.optDouble("tamax");
                String fint = jsonObj.optString("fint");
                String idema = jsonObj.optString("idema");
                String ubi = jsonObj.optString("ubi");
                if (Double.isNaN(tamin)) {
                    tamin = null;
                }
                if (Double.isNaN(tamax)) {
                    tamax = null;
                }

                WeatherList.add(new Weather(fint, idema, ubi, tamax, tamin));
            }
        }
        return WeatherList;
    }

}
