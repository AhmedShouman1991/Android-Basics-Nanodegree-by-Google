package com.example.android.quakereport;



import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public  class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String url;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        Log.e("loadInBackground", "started");

        if (url == null) {
            return null;
        }
        return ConnectAndJSONParse.getEarthquakeArrayList(url);
    }
}