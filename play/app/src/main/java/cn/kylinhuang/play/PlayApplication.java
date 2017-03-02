package cn.kylinhuang.play;

import android.app.Application;

/**
 * Created by kylinhuang on 28/02/2017.
 */

public class PlayApplication extends Application{

    private static PlayApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static PlayApplication getApplication() {
        return mApplication;
    }
}
