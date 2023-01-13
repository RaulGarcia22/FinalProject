package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class FileDatamart implements ReaderInterface {
    public JSONArray max = new JSONArray();
    public JSONArray min = new JSONArray();


    private void reader() throws IOException {
        String uri = "Datalake/datalake";
        File directorio = new File(uri);
        File[] listaArchivos = directorio.listFiles();
        JSONArray jsonArray = null;
        JSONArray maximos = new JSONArray();
        JSONArray minimos = new JSONArray();
        for (File archivo : listaArchivos) {
            jsonArray = new JSONArray();
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonArray.put(line);
            }
            List<Double> tamaxList = new ArrayList<>();
            List<Double> taminList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                String jsonObj = jsonArray.optString(i);
                jsonObj = jsonObj.replace("=", ":");
                JSONObject jsonObject = new JSONObject(jsonObj);
                double tamin = jsonObject.optDouble("tamin");
                double tamax = jsonObject.optDouble("tamax");
                taminList.add(tamin);
                tamaxList.add(tamax);
            }
            double max = Double.MIN_VALUE;
            int maxIndex = 0;
            double min = Double.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < tamaxList.size(); j++) {
                if (tamaxList.get(j) > max) {
                    max = tamaxList.get(j);
                    maxIndex = j;
                }
            }
            for (int i = 0; i < taminList.size(); i++) {
                if (taminList.get(i) < min) {
                    min = taminList.get(i);
                    minIndex = i;
                }
            }
            maximos.put(jsonArray.get(maxIndex));
            minimos.put(jsonArray.get(minIndex));
            reader.close();
        }
        max.putAll(maximos);
        min.putAll(minimos);
    }


    public JSONArray getMax() throws IOException {
        reader();
        return max;
    }

    public JSONArray getMin() throws IOException {
        reader();
        return min;
    }

}
