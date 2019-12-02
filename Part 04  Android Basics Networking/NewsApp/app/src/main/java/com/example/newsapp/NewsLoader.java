package com.example.newsapp;



import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public  class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    private String url;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public List<NewsItem> loadInBackground() {
        if (url == null) {
            return null;
        }
        return ConnectAndJSONParse.getNewsArrayList(url);
    }
}