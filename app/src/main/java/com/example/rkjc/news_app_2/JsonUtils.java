package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String JSONString) {
        JSONObject newsApp;
        JSONArray articles;
        JSONObject iterator;
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        try {
            newsApp= new JSONObject(JSONString);
            articles = newsApp.getJSONArray("articles");

            for (int i = 0; i < articles.length(); i++) {
                iterator = articles.getJSONObject(i);
                String author = iterator.getString("author");
                String title = iterator.getString("title");
                String description = iterator.getString("description");
                String url = iterator.getString("url");
                String urlToImage = iterator.getString("urlToImage");
                String publishedAt = iterator.getString("publishedAt");
                NewsItem article = new NewsItem(author, title, description, url, urlToImage, publishedAt);
                newsItems.add(article);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItems;
    }

}


