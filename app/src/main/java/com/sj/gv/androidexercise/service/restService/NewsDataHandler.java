package com.sj.gv.androidexercise.service.restService;

import com.sj.gv.androidexercise.model.NewsApiResponse;
import com.sj.gv.androidexercise.presenter.PresenterModelInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sj.gv.androidexercise.utils.AppUtils.parseError;

public class NewsDataHandler {

    private static PresenterModelInterface mPresenterModelInterface;


    public NewsDataHandler(PresenterModelInterface interfaceObj){
        mPresenterModelInterface = interfaceObj;
    }

    public void getNewsFeedsFromWeb(){
        RestApiService restApiService = RestClient.getRetrofitInstance().create(RestApiService.class);

        final Call<NewsApiResponse> deleteProfileRequest = restApiService.getNewsFeeds();
        deleteProfileRequest.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if(response.isSuccessful())
                    mPresenterModelInterface.onGetNewsFeedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                mPresenterModelInterface.onConnectivityError();
            }
        });
    }

    public static void responseError(int responseCode){
        if(responseCode != 200)
        mPresenterModelInterface.onGetNewsFeedFailure(parseError(responseCode));

    }
}
