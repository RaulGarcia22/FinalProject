package model;

import com.google.gson.Gson;
import controller.Weather;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileDatalake implements Datalake {
    String name;
    public String newDate;

    public void writeData(List<Weather> weatherList) throws IOException, ParseException {
        String uri = "Datalake/datalake";
        File directory = new File(uri);
        if (!directory.exists()) {
            directory.mkdir();
        }
        for (Weather weatherData : weatherList) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = format.parse(weatherData.getFint());
            DateFormat newFormat = new SimpleDateFormat("yyyyMMdd");
            newDate = newFormat.format(date);
            name = (uri + "/" + newDate + ".events");
            File file = new File(name);
            if (!file.exists()) {
                file.createNewFile();
            }
            Gson gson = new Gson();
            String wheatherLine = gson.toJson(weatherData);
            FileReader fileReader = new FileReader(name);
            BufferedReader reader = new BufferedReader(fileReader);
            boolean notwrite = true;
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.equals(wheatherLine)) {
                    notwrite = false;
                    break;
                }
            }
            if (notwrite) {
                FileWriter fileWriter = new FileWriter(name, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(wheatherLine);
                writer.newLine();
                writer.close();
            }
        }
    }
}
