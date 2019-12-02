package com.example.alexandriatourguidapp;

public class Item {

    private int nImgId;
    private String nName;
    private String nType;
    private String nOpeningHours;
    private String nLocation;


    public Item(int nImgId, String nName, String nType, String nOpeningHours, String nLocation) {
        this.nImgId = nImgId;
        this.nName = nName;
        this.nType = nType;
        this.nOpeningHours = nOpeningHours;
        this.nLocation = nLocation;
    }


    public int getnImgId() {
        return nImgId;
    }

    public String getnName() {
        return nName;
    }

    public String getnType() { return nType; }

    public String getnOpeningHours() {
        return nOpeningHours;
    }

    public String getnLocation() {
        return nLocation;
    }
}
