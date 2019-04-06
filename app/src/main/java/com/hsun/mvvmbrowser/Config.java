package com.hsun.mvvmbrowser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Config {

    public static SharedPreferences sharedPreferences;

    public static String HOME_URL = "http://www.hsunserver.ga/hsunresume";

    //SharedPreference
    public static String
            SP_HOME_URL = "SP_HOME_URL",
            SP_ENABLED_AD_BLOCK = "SP_ENABLED_AD_BLOCK",
            SP_ENABLED_OPEN_FLOAT_WINDOW = "SP_ENABLED_OPEN_FLOAT_WINDOW";

    public final static int REFRESH_WEB_SETTING = 1000;

    public static void init(Activity activity) {
        sharedPreferences = activity.getSharedPreferences(BuildConfig.APPLICATION_ID + "SP", Context.MODE_PRIVATE);
        HOME_URL = sharedPreferences.getString(SP_HOME_URL, HOME_URL);
    }
}
