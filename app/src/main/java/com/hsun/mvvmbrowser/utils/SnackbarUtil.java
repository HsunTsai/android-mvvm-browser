package com.hsun.mvvmbrowser.utils;


import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hsun.mvvmbrowser.R;

/**
 * Created by hsun on 2019/4/7.
 */

public class SnackbarUtil {

    public static void text(Activity activity, String text) {
        text(activity, text, null, null);
    }

    public static void text(Activity activity, String text, String actionMessage, View.OnClickListener onClickListener) {
        try {
            Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG);
            if (null != actionMessage && null != onClickListener)
                snackbar.setAction(actionMessage, onClickListener);
            SnackbarHelper.configSnackbar(activity, snackbar);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Snackbar progress(Activity activity) {
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content),
                activity.getString(R.string.common_processing), Snackbar.LENGTH_INDEFINITE);
        ProgressBar progressBar = new ProgressBar(activity);
        Integer size = UITransform.dp2px(activity, 25);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
//        Integer margin = UITransform.dp2px(context, 6);
//        layoutParams.setMargins(margin, margin, margin, margin);
        progressBar.setLayoutParams(layoutParams);
        ((ViewGroup) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)
                .getParent()).addView(progressBar, 0);
        return snackbar;
    }

    public static Snackbar progress(Activity activity, String message) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);
        ProgressBar progressBar = new ProgressBar(activity);
        Integer size = UITransform.dp2px(activity, 25);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
//        Integer margin = UITransform.dp2px(context, 6);
//        layoutParams.setMargins(margin, margin, margin, margin);
        progressBar.setLayoutParams(layoutParams);
        ((ViewGroup) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)
                .getParent()).addView(progressBar, 0);
        return snackbar;
    }

}