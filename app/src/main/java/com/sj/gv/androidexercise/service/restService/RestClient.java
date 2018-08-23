package com.sj.gv.androidexercise.service.restService;
import android.util.Log;

import com.sj.gv.androidexercise.utils.AppUtils;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sj.gv.androidexercise.utils.AppUtils.getCacheDirectory;

public class RestClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://dl.dropboxusercontent.com";

   private static int cacheSize = 2 * 1024 * 1024; //assuming we fix the cache size to 2 mb
    // internal cache more than the recommended limit will be first one to be cleared by the system
    // retrofit auto clears the cache if it exceeds the limit(in our case 2mb)

    public static Retrofit getRetrofitInstance() {

        Cache cache = new Cache(getCacheDirectory(), cacheSize);

        //making using of interceptor to interpret the erroneous response and show appropriate message to the user

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        okhttp3.Response response = chain.proceed(request);
                        Log.d("client", response.code()+"");
                        NewsDataHandler.responseError(response.code());
                        return response;
                    }
                })
                .cache(cache)
                .build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()) //using gson converter since our rest api response is in json format
                    .build();
        }
        return retrofit;
    }
}