package com.example.uplannerapk;

public class DatosMapas {
    private String lati;
    private String longi;

    public DatosMapas(String lati, String longi) {
        this.lati = lati;
        this.longi = longi;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }
}
