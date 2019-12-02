package com.example.android.quakereport;

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

public class ConnectAndJSONParse {

    private final static String LOG_TAG = ConnectAndJSONParse.class.getSimpleName();


    public static ArrayList<Earthquake> getEarthquakeArrayList(String url) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Earthquake> earthquakeArrayList;
        URL url1 = makeURL(url);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpConnection(url1);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        earthquakeArrayList = extractEarthquakes(jsonResponse);
//        Log.e("size" , "is" + earthquakeArrayList.size());
        return earthquakeArrayList;
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


    private static ArrayList<Earthquake> extractEarthquakes(String earthquakesJSON) {
        if (earthquakesJSON == null) {
            return null;
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        Earthquake earthquake;
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject root = new JSONObject(earthquakesJSON);
            JSONArray featuresArray = root.optJSONArray("features");

            for (int i = 0; i < featuresArray.length(); i++) {
                JSONObject quackFeature = featuresArray.optJSONObject(i);

                JSONObject properties = quackFeature.getJSONObject("properties");


                double mag = properties.getDouble("mag");
                String place = properties.getString("place");
                String time = properties.getString("time");
                String URL = properties.getString("url");

                long timeInMillSec = Long.parseLong(time);
                earthquake = new Earthquake(mag, place, timeInMillSec, URL);
                earthquakes.add(earthquake);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return earthquakes;
    }


}
