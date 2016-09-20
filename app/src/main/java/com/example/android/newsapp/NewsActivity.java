package com.example.android.newsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = NewsActivity.class.getName();
    private ProgressBar progressBar;
    private TextView emptyTextView;
    private static final String GUARDIAN_REQUEST_URL
            = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private NewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.v(LOG_TAG, "The value of isConnected is:" + isConnected);

        ListView newsListView = (ListView) findViewById(R.id.list);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        emptyTextView = (TextView) findViewById(R.id.empty_text_view);
        newsListView.setEmptyView(emptyTextView);

        ArrayList<News> newsTest = new ArrayList<News>();

        adapter = new NewsAdapter(this, newsTest);
        newsListView.setAdapter(adapter);

        if (isConnected) {
            Log.v(LOG_TAG, "initLoader()");
            getSupportLoaderManager().initLoader(1, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText(R.string.no_connection);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        progressBar.setVisibility(View.GONE);
        Log.v(LOG_TAG, "onLoadFinished()");
        if (newsList == null || newsList.isEmpty()) {
            emptyTextView.setText(R.string.no_data_found);
            return;
        }
        adapter.clear();
        adapter.addAll(newsList);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.v(LOG_TAG, "onLoaderReset()");
        adapter.clear();
    }
}
