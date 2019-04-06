package com.hsun.mvvmbrowser.activities.setting;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.hsun.mvvmbrowser.Config;
import com.hsun.mvvmbrowser.R;
import com.hsun.mvvmbrowser.components.DialogHelper;
import com.hsun.mvvmbrowser.databinding.ActivitySettingBinding;

public class SettingViewModel extends ViewModel {

    private Activity activity;
    public Boolean isChange = false;
    public final ObservableField<String> homeURL = new ObservableField<>(Config.HOME_URL);

    SettingViewModel(Activity activity, ActivitySettingBinding activitySettingBinding) {
        this.activity = activity;
        activitySettingBinding.switchAdBlock.setChecked(Config.sharedPreferences.getBoolean(Config.SP_ENABLED_AD_BLOCK, false));
        Log.e("isCheck!!!",String.valueOf(Config.sharedPreferences.getBoolean(Config.SP_ENABLED_AD_BLOCK, false)));
    }

    boolean getChangeState() {
        return isChange;
    }

    public void onSwitchChangeADBlock(boolean isChecked) {
        Config.sharedPreferences.edit().putBoolean(Config.SP_ENABLED_AD_BLOCK, isChecked).apply();
        isChange = true;
    }

    public void changeHomePage(View view) {
        new DialogHelper(activity)
                .setType(DialogHelper.TYPE.INPUT)
                .setTitle(activity.getString(R.string.setting_home))
                .setDialogPositiveListener(new DialogHelper.PositiveListener() {
                    @Override
                    public void onClick(String text) {
                        Config.sharedPreferences.edit()
                                .putString(Config.SP_HOME_URL, text).apply();
                        Config.HOME_URL = text;
                        homeURL.set(text);
                    }
                })
                .show();
    }
}
