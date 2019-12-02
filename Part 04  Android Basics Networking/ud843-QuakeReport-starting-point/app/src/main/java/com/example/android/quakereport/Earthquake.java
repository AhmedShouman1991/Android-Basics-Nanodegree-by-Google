package com.example.android.quakereport;

public class Earthquake {
    private double magn;
    private String place;
    private Long time;
    private String URL;

    public Earthquake(double magn, String place, Long time, String URL) {
        this.magn = magn;
        this.place = place;
        this.time = time;
        this.URL = URL;
    }

    public double getMagn() {
        return magn;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public String getURL() {
        return URL;
    }
}
