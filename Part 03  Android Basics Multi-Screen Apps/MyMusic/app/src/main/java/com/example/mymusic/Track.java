package com.example.mymusic;

public class Track {
    private String title;
    private String artist;
    private int imgId;

    public Track(String title, String artist, int imgId) {
        this.title = title;
        this.artist = artist;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getImgId() {
        return imgId;
    }
}
