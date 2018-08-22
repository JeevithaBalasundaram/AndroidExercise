package com.sj.gv.androidexercise.presenter;

import com.sj.gv.androidexercise.model.NewsApiResponse;

public interface PresenterModelInterface {

    void onGetNewsFeedSuccess(NewsApiResponse newsResponse);

    void onGetNewsFeedFailure(String errorMessage);

    void onConnectivityError();

}