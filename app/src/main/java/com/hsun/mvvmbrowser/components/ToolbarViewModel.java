package com.hsun.mvvmbrowser.components;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.hsun.mvvmbrowser.R;

public class ToolbarViewModel extends ViewModel {

    private Activity activity;
    private Listener listener;

    public final ObservableField<String>
            title = new ObservableField<>(""),
            actionText = new ObservableField<>("");

    public final ObservableBoolean
            titleShow = new ObservableBoolean(false),
            actionShow = new ObservableBoolean(false);

    public ToolbarViewModel(Activity activity) {
        this.activity = activity;
        actionText.set(activity.getString(R.string.common_complete));
    }

    public interface Listener {
        public void onBackPress();

        public void onActionPress();
    }

    public ToolbarViewModel setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public ToolbarViewModel setTitle(String text) {
        title.set(text);
        setTitleShow(true);
        return this;
    }

    public ToolbarViewModel setTitleShow(Boolean show) {
        titleShow.set(show);
        return this;
    }

    public ToolbarViewModel setActionText(String text) {
        actionText.set(text);
        setActionShow(true);
        return this;
    }

    public ToolbarViewModel setActionShow(Boolean show) {
        actionShow.set(show);
        return this;
    }

    public void onBackPress(View view) {
        if (null != listener) listener.onBackPress();
    }

    public void onActionPress(View view) {
        if (null != listener) listener.onActionPress();
    }
}
