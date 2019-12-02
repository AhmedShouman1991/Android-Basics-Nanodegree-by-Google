package com.example.newsapp;

import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ConnectAndJSONParse {

    private final static String LOG_TAG = ConnectAndJSONParse.class.getSimpleName();


    public static ArrayList<NewsItem> getNewsArrayList(String url) {

        ArrayList<NewsItem> newsArrayList;
        URL url1 = makeURL(url);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpConnection(url1);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        newsArrayList = extractNewsItems(jsonResponse);
        return newsArrayList;
    }


    private static URL makeURL(String url) {
        URL urlObject = null;

        if (url == null) {
            return null;
        }
        try {
            urlObject = new URL(url);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error in creating URL", e);
        }
        return urlObject;
    }


    private static String makeHttpConnection(URL url) throws IOException {
        String jsonResponse = null;

        if (url == null) {
            return null;
        }
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() != 200) {
                Log.e(LOG_TAG, "Error with server response, response code = " + httpURLConnection.getResponseCode());
            } else {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in makeHttpConnection Method: Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream == null) {
            return null;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            output.append(line);
            line = bufferedReader.readLine();
        }
        return output.toString();
    }

    private static ArrayList<NewsItem> extractNewsItems(String newsJSON) {
        if (newsJSON == null) {
            return null;
        }
        ArrayList<NewsItem> newsItemArrayList = new ArrayList<>();

        NewsItem newsItem;
        try {
            JSONObject root = new JSONObject(newsJSON);
            JSONObject response = root.optJSONObject("response");
            JSONArray resultsArray = response.optJSONArray("results");

            if (resultsArray != null && resultsArray.length() > 0) {
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject newsObject = resultsArray.optJSONObject(i);
                    String title = newsObject.getString("webTitle");
                    String section = newsObject.getString("sectionName");
                    String articleTime = newsObject.optString("webPublicationDate");
                    JSONArray tags = newsObject.optJSONArray("tags");
                    String author = "";
                    if (tags != null && tags.length() > 0) {
                        JSONObject authorTag = tags.optJSONObject(0);
                        String firstName = authorTag.optString("firstName");
                        String lastName = authorTag.optString("lastName");
                        author = firstName + " " + lastName;
                    }
                    String url = newsObject.optString("webUrl");
                    Log.e("result:" + i, "data:" + title + section + author + articleTime + url);
                    newsItem = new NewsItem(title, section, author, articleTime, url);
                    newsItemArrayList.add(newsItem);
                }
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the Guardian JSON results", e);
        }
        return newsItemArrayList;
    }


}
