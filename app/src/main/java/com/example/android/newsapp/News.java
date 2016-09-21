package com.example.android.newsapp;

/**
 * Created by Zohaib on 20/09/16.
 */
public class News {
    private String title;
    private String sectionName;
    private String url;
    private String author;
    public News(String title, String author, String sectionName, String url) {
        this.title = title;
        this.sectionName = sectionName;
        this.url = url;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }
}
