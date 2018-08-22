package com.sj.gv.androidexercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse {

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private List<NewsFeed> feeds = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NewsFeed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<NewsFeed> feeds) {
        this.feeds = feeds;
    }




}
