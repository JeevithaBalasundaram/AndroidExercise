package com.sj.gv.androidexercise;

import android.app.Application;

public class AndroidApplication extends Application {

    private static AndroidApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static AndroidApplication getContext(){
        return mContext;
    }
}

