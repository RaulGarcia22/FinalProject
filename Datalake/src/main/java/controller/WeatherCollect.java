package controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

import org.json.JSONArray;

public class WeatherCollect {
    String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
    public JSONArray filteredArray;

    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXVsbnVlejIyMkBnbWFpbC5jb20iLCJqdGkiOiIyYTg5NTQxMS1iNGU1LTQ1YmQtYTMwNC05NTkzMWY3ZTI1OTciLCJpc3MiOiJBRU1FVCIsImlhdCI6MTY3MjY3MzcxOSwidXNlcklkIjoiMmE4OTU0MTEtYjRlNS00NWJkLWEzMDQtOTU5MzFmN2UyNTk3Iiwicm9sZSI6IiJ9.es3lP9tB4jiJ_Gif8I5NSFXFQuvCe43u7gu7i9sn4QY";
    String response;

    {
        try {
            response = Jsoup.connect(url)
                    .validateTLSCertificates(false)
                    .timeout(6000)
                    .ignoreContentType(true)
                    .header("accept", "application/json")
                    .header("api_key", apiKey)
                    .method(Connection.Method.GET)
                    .maxBodySize(0).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject responsejson = new JSONObject(response);
        String urldatos = responsejson.getString("datos");
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(urldatos);
            HttpResponse response = httpClient.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONArray jsonArray = new JSONArray(jsonString);
            filteredArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                float lon = obj.optFloat("lon");
                float lat = obj.optFloat("lat");
                if (lat > 27.5 && lat < 28.4) {
                    if (-16.0 < lon && -15.0 > lon) {
                        filteredArray.put(obj);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray getFilteredArray() {
        return filteredArray;
    }
}
