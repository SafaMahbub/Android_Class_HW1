package com.example.rkjc.news_app_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar)findViewById(R.id.progress);
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        mAdapter = new NewsAdapter(this, newsItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private URL makeNewsAppSearchQuery() {
        URL newsappSearchUrl = NetworkUtils.buildUrl();
        String urlString = newsappSearchUrl.toString();
        Log.d("mycode", urlString);
        return newsappSearchUrl;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            URL url = makeNewsAppSearchQuery();
            NewsQueryTask task = new NewsQueryTask();
            task.execute(url);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class NewsQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL ... urls){
            String newsappSearchResults = "";
            try {
                newsappSearchResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsappSearchResults;
        }

        @Override
        protected void onPostExecute(String newsSearchResults) {
            Log.d("mycode", newsSearchResults);
            super.onPostExecute(newsSearchResults);
            mProgressBar.setVisibility(View.GONE);
            newsItems = JsonUtils.parseNews(newsSearchResults);
            mAdapter.mNewsItems.addAll(newsItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
