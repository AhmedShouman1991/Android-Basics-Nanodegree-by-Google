package com.example.newsapp;

public class NewsItem {
    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String time;
    private String URL;

    public NewsItem(String mTitle, String mSection, String mAuthor, String time, String URL) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mAuthor = mAuthor;
        this.time = time;
        this.URL = URL;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getTime() {
        return time;
    }

    public String getURL() {
        return URL;
    }
}
