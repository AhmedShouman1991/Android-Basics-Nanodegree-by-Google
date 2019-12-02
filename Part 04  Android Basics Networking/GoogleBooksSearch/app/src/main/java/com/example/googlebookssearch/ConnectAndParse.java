package com.example.googlebookssearch;

import android.util.Log;

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
import java.util.List;

public class ConnectAndParse {
    private final static String LOG_TAG = ConnectAndParse.class.getName();


    public static List<Book> getBooksList(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        String jsonResponse = null;
        try {
            URL url1 = getUrl(url);
            jsonResponse = makeHttpConnection(url1);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in getBooksList Method");
        }
       return parseJSON(jsonResponse);
    }


    private static URL getUrl(String url) {
        if (url == null) {
            return null;
        }
        URL urlObject = null;
        try{
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error in getting URL");
        }
        return urlObject;
    }

    private static String makeHttpConnection(URL url) throws IOException {
        if (url == null) {
            return null;
        }
        HttpURLConnection httpURLConnection =null;
        InputStream stream = null;
        String jsonResponse = null;
        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int statue = httpURLConnection.getResponseCode();
            if (statue != 200) {
                Log.e(LOG_TAG, "Error in server response, Response code = " + statue);
            }else {
                stream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(stream);
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in makeHttpConnection Method");
        }
        finally {
            if (stream != null) { stream.close();}
            if (httpURLConnection != null) { httpURLConnection.disconnect();}
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream stream) {
        if (stream == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in read data from stream");
        }
        return stringBuilder.toString();
    }

    private static List<Book> parseJSON(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }
        List<Book> books = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray itemsArray = root.optJSONArray("items");

            if (itemsArray != null && itemsArray.length() > 0) {
                for (int i = 0; i < itemsArray.length() ; i++) {
                    JSONObject bookVolume = itemsArray.getJSONObject(i);
                    JSONObject bookInfo = bookVolume.getJSONObject("volumeInfo");
                    String bookTitle = bookInfo.getString("title");
                    JSONArray authorsArray = bookInfo.optJSONArray("authors");
                    StringBuilder authorsBuilder = new StringBuilder();
                    if (authorsArray != null && authorsArray.length() > 0) {
                        for (int j = 0; j < authorsArray.length(); j++) {
                            authorsBuilder.append(authorsArray.getString(j));
                            if (j != authorsArray.length() - 1) {
                                authorsBuilder.append(", ");
                            }
                        }
                    }
                    String authors = authorsBuilder.toString();
                    Book book = new Book(bookTitle, authors);
                    books.add(book);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }
}


