package com.hsun.mvvmbrowser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Config {

    public static SharedPreferences sharedPreferences;

    public static String HOME_PAGE = "http://www.hsunserver.ga/hsunresume",
            WEB_VIEW_ENABLED_AD_BLOCK = "WEB_VIEW_ENABLED_AD_BLOCK";

    public final static int REFRESH_WEB_SETTING = 1000;

    public static void init(Activity activity) {
        sharedPreferences = activity.getSharedPreferences(BuildConfig.APPLICATION_ID + "SP", Context.MODE_PRIVATE);
    }
}
