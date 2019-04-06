package com.hsun.jumpbrowser.activities.setting;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.hsun.jumpbrowser.R;
import com.hsun.jumpbrowser.databinding.ActivitySettingBinding;

public class SettingActivity extends Activity {

    private ActivitySettingBinding activitySettingBinding;
    private SettingViewModel settingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        activitySettingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        settingViewModel = new SettingViewModel(this);
        activitySettingBinding.setViewModel(settingViewModel);

    }
}
