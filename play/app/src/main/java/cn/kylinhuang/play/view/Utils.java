package cn.kylinhuang.play.view;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by kylinhuang on 02/03/2017.
 */

public class Utils {

    public static int getScreenWidth(Activity activity){
        if (activity != null) {
            DisplayMetrics metric = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
            return metric.widthPixels;     // 屏幕宽度（像素）
        }
        return 0;
    }

    public static int getScreenHeight(Activity activity){
        if (activity != null) {
            DisplayMetrics metric = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
            return metric.heightPixels;     // 屏幕高度（像素）
        }
        return 0;
    }
}
