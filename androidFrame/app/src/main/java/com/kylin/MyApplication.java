package com.kylin;

import android.app.Application;

/**
 * Created by kylinhuang on 15/11/2016.
 */

public class MyApplication  extends Application{
    private static MyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static MyApplication getApplication() {
        return mContext;
    }
}
