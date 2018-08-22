package com.sj.gv.androidexercise.view.viewinterface;

import com.sj.gv.androidexercise.model.NewsApiResponse;

public interface ViewPresenterInterface {

    void updateNewsList(NewsApiResponse newsResponse);

    void displayErrorNotification(String errorMessage);

    void displayNoNetworkMessage();
}
