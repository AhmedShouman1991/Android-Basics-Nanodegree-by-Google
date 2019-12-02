package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {

    public static final String LOG_TAG = MainActivity.class.getName();
    private List<NewsItem> news = new ArrayList<>();
    private Adapter adapter = null;
    private TextView noDataText;
    private ProgressBar progressSpinner;
    private static final String GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressSpinner = findViewById(R.id.loading_spinner);
        noDataText = findViewById(R.id.no_data_textView);
        ListView NewsListView = findViewById(R.id.list);
        NewsListView.setEmptyView(noDataText);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            progressSpinner.setVisibility(View.GONE);
            noDataText.setText(R.string.no_Internet_Connection);
        }


        adapter = new Adapter(
                MainActivity.this, 0, news);

        NewsListView.setAdapter(adapter);

        NewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsItem item = adapter.getItem(i);
                String url = item.getURL();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, @Nullable Bundle args) {
        Uri uri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = uri.buildUpon();
        uriBuilder.appendQueryParameter("q","usa");
        uriBuilder.appendQueryParameter("show-tags","contributor");
        uriBuilder.appendQueryParameter("api-key","1d5317d1-bba1-41b5-b4e8-46f48b0437d6");
        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsItem>> loader, List<NewsItem> data) {
        progressSpinner.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {
            adapter.clear();
            adapter.addAll(data);
        } else {
            noDataText.setText(R.string.no_news);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsItem>> loader) {
        adapter.clear();
    }
}