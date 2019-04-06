package com.hsun.mvvmbrowser.activities.setting;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.hsun.mvvmbrowser.R;
import com.hsun.mvvmbrowser.components.ToolbarViewModel;
import com.hsun.mvvmbrowser.databinding.ActivitySettingBinding;

public class SettingActivity extends Activity {

    private ActivitySettingBinding activitySettingBinding;
    private SettingViewModel settingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        activitySettingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        settingViewModel = new SettingViewModel(this);
        activitySettingBinding.setViewModel(settingViewModel);

        ToolbarViewModel toolbarViewModel = new ToolbarViewModel(this)
                .setListener(new ToolbarViewModel.Listener() {
                    @Override
                    public void onBackPress() {
                        onBackPressed();
                    }

                    @Override
                    public void onActionPress() {

                    }
                });
        toolbarViewModel.setTitle(getString(R.string.common_setting));
        activitySettingBinding.setToolbarViewModel(toolbarViewModel);
    }

    @Override
    public void onBackPressed() {
        if (settingViewModel.getChangeState()) setResult(RESULT_OK);
        finish();
    }
}
