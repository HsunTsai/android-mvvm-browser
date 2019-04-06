package com.hsun.jumpbrowser.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.adblockplus.libadblockplus.android.webview.AdblockWebView;

public class WebViewSetting {

    private AdblockWebView webView;
    private Listener listener;

    public WebViewSetting(AdblockWebView webView) {
        this.webView = webView;
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                if (null != listener) listener.onUrlChange(urlNewString);
                webView.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (null != listener) {
                    listener.canGo(view.canGoBack(), view.canGoForward());
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
//                Log.e("progress", String.valueOf(progress));
                if (null != listener) listener.onProgressChange(progress);
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        public void onProgressChange(int progress);

        public void onUrlChange(String url);

        public void canGo(boolean canGoBack, boolean canGoForward);

    }
}
