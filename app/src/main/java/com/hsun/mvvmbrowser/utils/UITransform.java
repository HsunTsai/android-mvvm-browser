package com.hsun.mvvmbrowser.utils;

import android.app.Activity;
import android.content.Context;

/**
 * Created by hsun on 2019/4/7.
 */

public class UITransform {
    public static int dp2px(Activity activity, float dp) {
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Activity activity, float px) {
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
