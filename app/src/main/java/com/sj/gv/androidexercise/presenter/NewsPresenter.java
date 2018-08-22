package com.sj.gv.androidexercise.presenter;

import com.sj.gv.androidexercise.model.NewsApiResponse;
import com.sj.gv.androidexercise.service.restService.NewsDataHandler;
import com.sj.gv.androidexercise.view.viewinterface.ViewPresenterInterface;


public class NewsPresenter implements PresenterModelInterface {
    private ViewPresenterInterface mViewPresenterInterface;
    private NewsDataHandler mNewsDataHandler;


    public void getNewsFeeds(){
        mNewsDataHandler.getNewsFeedsFromWeb();
    }

    public NewsPresenter(ViewPresenterInterface newsView){
        mViewPresenterInterface = newsView;
        mNewsDataHandler = new NewsDataHandler(this);

    }
    @Override
    public void onGetNewsFeedFailure(String errorMessage) {
        mViewPresenterInterface.displayErrorNotification(errorMessage);
    }

    @Override
    public void onGetNewsFeedSuccess(NewsApiResponse newsResponse) {
        mViewPresenterInterface.updateNewsList(newsResponse);
    }

    @Override
    public void onConnectivityError() {
        mViewPresenterInterface.displayNoNetworkMessage();
    }
}
