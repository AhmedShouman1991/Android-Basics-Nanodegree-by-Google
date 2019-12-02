package com.example.googlebookssearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private List<Book> booksList = new ArrayList<>();
    private ProgressBar progressSpinner;
    private TextView noDataText;
    private SearchView searchView;
    private String searchQuarry = null;
    private String lastSearch= null;
    private BooksArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search);
        final ListView listView = findViewById(R.id.list);
        noDataText = findViewById(R.id.no_data_text);
        listView.setEmptyView(noDataText);
        progressSpinner = findViewById(R.id.progress_circle);
        progressSpinner.setVisibility(View.GONE);


      if (isConnected()) {
          progressSpinner.setVisibility(View.VISIBLE);
          noDataText.setText("");
          getSupportLoaderManager().initLoader(0, null, this);
      } else {
          noDataText.setText(R.string.no_connection);
      }
      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

          @Override
          public boolean onQueryTextSubmit(String query) {
              searchQuarry = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=10";
              if (isConnected()) {
                  noDataText.setText("");
                  adapter.clear();
                  progressSpinner.setVisibility(View.VISIBLE);
                  getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
              }else {
                  adapter.clear();
                  noDataText.setText(R.string.no_connection);
              }
              return true;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
              return false;
          }
      });

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            ConnectivityManager cm =
//                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//            boolean isConnected = activeNetwork != null &&
//                    activeNetwork.isConnectedOrConnecting();
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                progressSpinner.setVisibility(View.VISIBLE);
//                if (lastSearch == null) {
//                    searchQuarry += (query + "&maxResults=3");
//                    if (isConnected) {
//                        getSupportLoaderManager().initLoader(0, null, MainActivity.this);
//                    } else {
//                        progressSpinner.setVisibility(View.GONE);
//                        noDataText.setText(R.string.no_connection);
//                    }
//                    lastSearch = query;
//                    return true;
//                } else if (query.equals(lastSearch)) {
//                    progressSpinner.setVisibility(View.GONE);
//                    return false;
//                } else {
//                    searchQuarry = "https://www.googleapis.com/books/v1/volumes?q=";
//                    searchQuarry += (query + "&maxResults=3");
//                    if (isConnected) {
//                        getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
//                        //getSupportLoaderManager().initLoader(0, null, MainActivity.this);
//                    } else {
//                        progressSpinner.setVisibility(View.GONE);
//                        noDataText.setText(R.string.no_connection);
//                    }
//                    lastSearch = query;
//                    return true;
//                }
//            }
//        });


        adapter = new BooksArrayAdapter(this, booksList);
        listView.setAdapter(adapter);

    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.e("onCreateLoader", "started url = " + this.searchQuarry);
        return new BooksLoader(MainActivity.this, this.searchQuarry);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {
        Log.e("onLoaderFinished", "started");
        progressSpinner.setVisibility(View.GONE);
        if (data != null && data.size() > 0) {
            adapter.clear();
            adapter.addAll(data);
        } else {
            noDataText.setText(R.string.no_data_found);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        adapter.clear();
    }

    private boolean isConnected () {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return   activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }
}
