package com.example.googlebookssearch;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class BooksLoader extends AsyncTaskLoader<List<Book>> {
    private String url;
    public BooksLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        Log.e("loadInBackground", "started");
        if (url == null) {
            return null;
        }
        return ConnectAndParse.getBooksList(url);
    }
}
