package com.hsun.mvvmbrowser.activities.setting;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;

public class SettingViewModel extends ViewModel {

    private Activity activity;

    SettingViewModel(Activity activity) {
        this.activity = activity;
    }

}
