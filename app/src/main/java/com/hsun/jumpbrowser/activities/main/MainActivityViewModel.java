package com.hsun.jumpbrowser.activities.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.TextView;

import com.hsun.jumpbrowser.Config;
import com.hsun.jumpbrowser.activities.setting.SettingActivity;
import com.hsun.jumpbrowser.utils.Keyboard;
import com.hsun.jumpbrowser.utils.WebViewSetting;
import com.hsun.jumpbrowser.databinding.ActivityMainBinding;

import org.adblockplus.libadblockplus.android.webview.AdblockWebView;

public class MainActivityViewModel extends ViewModel {

    private Activity activity;
    private ActivityMainBinding activityMainBinding;
    private AdblockWebView webView;
    public final ObservableInt webProgress = new ObservableInt(0);
    public final ObservableBoolean webCanGoBack = new ObservableBoolean(false),
            webCanGoForward = new ObservableBoolean(false);

    MainActivityViewModel(Activity activity, final ActivityMainBinding activityMainBinding) {
        this.activity = activity;
        this.activityMainBinding = activityMainBinding;

        activityMainBinding.container.edSearchUrl.setText(Config.HOME_PAGE);

        webView = activityMainBinding.container.webView;
        setWebSetting();

        activityMainBinding.container.imgbtWebBack.setEnabled(false);
        activityMainBinding.container.imgbtWebForward.setEnabled(false);

        new WebViewSetting(webView)
                .setListener(new WebViewSetting.Listener() {
                    @Override
                    public void onProgressChange(int progress) {
                        webProgress.set(progress);
                    }

                    @Override
                    public void onUrlChange(String url) {
                        activityMainBinding.container.edSearchUrl.setText(url);
                    }

                    @Override
                    public void canGo(boolean canGoBack, boolean canGoForward) {
                        activityMainBinding.container.imgbtWebBack.setEnabled(canGoBack);
                        activityMainBinding.container.imgbtWebForward.setEnabled(canGoForward);
                        webCanGoBack.set(canGoBack);
                        webCanGoForward.set(canGoForward);
                    }
                });
        webView.loadUrl(Config.HOME_PAGE);

        activityMainBinding.container.edSearchUrl.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(null);
                    return true;
                }
                return false;
            }
        });
    }

    public void setWebSetting() {
        if (null != Config.sharedPreferences) {
            webView.setAdblockEnabled(Config.sharedPreferences.getBoolean(Config.WEB_VIEW_ENABLED_AD_BLOCK, true));
        }
    }

    /**
     * onClick
     * 搜尋
     *
     * @param view
     */
    public void search(View view) {
        Keyboard.hide(activity);
        activityMainBinding.container.edSearchUrl.clearFocus();
        String searchText = activityMainBinding.container.edSearchUrl.getText().toString();
        if (URLUtil.isValidUrl(searchText)) {
            webView.loadUrl(searchText);
        } else {
            if (searchText.startsWith("www")) {
                webView.loadUrl("https://" + searchText);
            } else {
                webView.loadUrl("https://www.google.com/search?q=" + searchText);
            }
        }
    }

    /**
     * onClick
     * Web返回
     *
     * @param view
     */
    public void webBack(View view) {
        webView.goBack();
    }

    /**
     * onClick
     * Web前往
     *
     * @param view
     */
    public void webForward(View view) {
        webView.goForward();
    }

    /**
     * onClick
     * Web回首頁
     *
     * @param view
     */
    public void webHome(View view) {
        webView.goForward();
    }

    /**
     * onClick
     * Web設定
     *
     * @param view
     */
    public void webSetting(View view) {
        Intent goSetting = new Intent(activity, SettingActivity.class);
        activity.startActivityForResult(goSetting, Config.REFRESH_WEB_SETTING);
    }

    /**
     * onClick
     * Web浮動視窗跳出
     *
     * @param view
     */
    public void webJump(View view) {

    }
}
