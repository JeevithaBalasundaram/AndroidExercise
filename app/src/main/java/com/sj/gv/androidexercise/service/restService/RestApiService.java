package com.sj.gv.androidexercise.service.restService;

import com.sj.gv.androidexercise.model.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {


    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<NewsApiResponse> getNewsFeeds();
}
