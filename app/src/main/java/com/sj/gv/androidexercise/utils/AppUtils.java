package com.sj.gv.androidexercise.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.sj.gv.androidexercise.AndroidApplication;
import com.sj.gv.androidexercise.R;
import com.sj.gv.androidexercise.model.NewsApiResponse;
import com.sj.gv.androidexercise.model.NewsFeed;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    public static String parseError(int errorCode){

        Resources res = AndroidApplication.getContext().getResources();
        String error;

        switch (errorCode){
            case 500:
                error = res.getString(R.string.server_broken);
                break;
            case 404:
                error = res.getString(R.string.page_not_found);
                break;
            case 408:
                error = res.getString(R.string.request_timeout);
                break;
            default:
                error = res.getString(R.string.generic_error_msg);
                break;
        }
        return error;
    }

    public static File getCacheDirectory(){
        Context context = AndroidApplication.getContext();
        File cache;
        if (hasExternalStorage()) {
            cache = context.getExternalCacheDir();
        } else {
            cache = context.getCacheDir(); //if external cache is not available get internal cache
        }
       File dataCache = new File(cache , context.getResources().getString(R.string.app_name));
        if (!dataCache.exists())
            dataCache.mkdirs();  //if the directory does not exists, create one
        Log.d("Utils", dataCache.getAbsolutePath());

        return dataCache;
    }


    private static boolean hasExternalStorage(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) { //check if external storage is present and if it has read and write access
            return true;
        }
        return false;
    }

}