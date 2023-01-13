package controller;


public class Weather {
    public String fint;
    public String idema;
    public String ubi;
    public Double tamax;
    public Double tamin;

    public String getFint() {
        return fint;
    }

    public Weather(String fint, String idema, String ubi, Double tamax, Double tamin) {
        this.fint = fint;
        this.idema = idema;
        this.ubi = ubi;
        this.tamax = tamax;
        this.tamin = tamin;
    }
}



