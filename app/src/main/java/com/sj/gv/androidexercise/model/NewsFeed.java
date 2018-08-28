package com.sj.gv.androidexercise.model;

import com.google.gson.annotations.SerializedName;

public class NewsFeed {

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("imageHref")
    public String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
