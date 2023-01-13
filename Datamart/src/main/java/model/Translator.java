package model;

import org.json.JSONObject;

public class Translator {
    private static final String INSERT_MAX =
            "INSERT INTO Tempmax VALUES ('%s', '%s', '%s', '%s', '%s' )";

    public static String getMax(Object obj) {
        String jsonObj = obj.toString();
        jsonObj = jsonObj.replace("=", ":");
        JSONObject jsonObject = new JSONObject(jsonObj);
        double tamax = jsonObject.optDouble("tamax");
        String fint = jsonObject.optString("fint");
        int index = fint.indexOf('T');
        String date = fint.substring(0, index);
        String time = fint.substring(index + 1);
        String idema = jsonObject.optString("idema");
        String ubi = jsonObject.optString("ubi");
        String format = String.format(INSERT_MAX,
                date, idema, ubi, time, tamax);
        return format;
    }

    private static final String INSERT_MIN =
            "INSERT INTO Tempmin VALUES ('%s', '%s', '%s', '%s', '%s' )";

    public static String getMin(Object obj) {
        String jsonObj = obj.toString();
        jsonObj = jsonObj.replace("=", ":");
        JSONObject jsonObject = new JSONObject(jsonObj);
        double tamin = jsonObject.optDouble("tamin");
        String fint = jsonObject.optString("fint");
        int index = fint.indexOf('T');
        String date = fint.substring(0, index);
        String time = fint.substring(index + 1);
        String idema = jsonObject.optString("idema");
        String ubi = jsonObject.optString("ubi");
        String format = String.format(INSERT_MIN,
                date, idema, ubi, time, tamin);
        return format;
    }
}