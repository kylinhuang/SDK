package cn.kylinhuang.play.view;

import android.app.Activity;
import android.content.Context;
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


    /** dip转换px */
    public static int dip2px(Context context , int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** 获取dimen值 */
    public static int getDimens(Context context  ,int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

}
