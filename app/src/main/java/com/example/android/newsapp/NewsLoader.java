package com.example.android.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by Zohaib on 20/09/16.
 */
public class NewsLoader extends AsyncTaskLoader<List<News>> {

    List<News> newsList;
    String urls[];

    public NewsLoader(Context context, String... urls) {
        super(context);
        this.urls = urls;
    }

    @Override
    public List<News> loadInBackground() {
        if (urls[0] == null || urls.length < 1) {
            return null;
        }
        String newsData = QueryUtils.fetchNewsData(urls[0]);

        Log.v("QueryUtils.java", "Fetched data: " + newsData);

        newsList = QueryUtils.extractNews(newsData);
        return newsList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
