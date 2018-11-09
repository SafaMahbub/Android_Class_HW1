package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    //    private static String base_url = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=";
    final static String BASE_URL = "https://newsapi.org/v1/articles?";

    final static String SOURCE_PARAM = "source";
    final static String SORT_PARAM = "sortBy";
    final static String APIKEY_PARAM = "apiKey";

    final static String SOURCE_PARAMETER = "the-next-web";
    final static String SORT_PARAMETER = "latest";
    final static String APIKEY_PARAMETER = "cf5c0ad283ba49a3a168d94fdf34cb48";


    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(SOURCE_PARAM, SOURCE_PARAMETER)
                .appendQueryParameter(SORT_PARAM, SORT_PARAMETER)
                .appendQueryParameter(APIKEY_PARAM, APIKEY_PARAMETER)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
