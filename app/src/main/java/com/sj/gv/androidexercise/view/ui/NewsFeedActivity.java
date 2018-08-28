package com.sj.gv.androidexercise.view.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sj.gv.androidexercise.model.NewsApiResponse;
import com.sj.gv.androidexercise.utils.AppUtils;
import com.sj.gv.androidexercise.view.adapter.NewsFeedAdapter;
import com.sj.gv.androidexercise.R;
import com.sj.gv.androidexercise.presenter.NewsPresenter;
import com.sj.gv.androidexercise.model.NewsFeed;
import com.sj.gv.androidexercise.view.viewinterface.ViewPresenterInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedActivity extends AppCompatActivity implements ViewPresenterInterface{

    private final String TAG = NewsFeedActivity.class.getSimpleName();

    private ProgressDialog mProgressDialog;
    private NewsPresenter mNewsPresenter;

    @BindView(R.id.refreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.news_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        ButterKnife.bind(this);


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setProgress(0);

        //setting swipe listener
        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);


        mNewsPresenter = new NewsPresenter(this);

        //request for data to the presenter
        mNewsPresenter.getNewsFeeds();
        mProgressDialog.show();
    }

    @Override
    public void updateNewsList(NewsApiResponse newsResponse) {
        if(mProgressDialog.isShowing()) mProgressDialog.dismiss();

        if(mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
        List<NewsFeed> newsFeeds = newsResponse.getFeeds();
        String pageTitle = newsResponse.getTitle();
        if(!pageTitle.isEmpty())
            getSupportActionBar().setTitle(pageTitle);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        NewsFeedAdapter mNewsFeedAdapter = new NewsFeedAdapter(this);
        if(newsFeeds !=  null) {
            mNewsFeedAdapter.setFeedsList(newsFeeds);
            mRecyclerView.setAdapter(mNewsFeedAdapter);
        }
    }

    @Override
    public void displayErrorNotification(String errorMessage) {
        if(mProgressDialog.isShowing()) mProgressDialog.dismiss();
        showAlert(errorMessage);
    }

    @Override
    public void displayNoNetworkMessage() {
        if(mProgressDialog.isShowing()) mProgressDialog.dismiss();
        showAlert(getString(R.string.no_network_message));
    }


    private void showAlert(String message){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message)
                .setNeutralButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        alertDialog.show();
    }

    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //request for reloading data on refresh
            mNewsPresenter.getNewsFeeds();
        }
    };
}
