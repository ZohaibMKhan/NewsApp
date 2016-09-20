package com.example.android.newsapp;

/**
 * Created by Zohaib on 20/09/16.
 */
public class News {
    private String title;
    private String sectionName;
    private String url;
    public News(String title, String sectionName, String url) {
        this.title = title;
        this.sectionName = sectionName;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getUrl() {
        return url;
    }
}
