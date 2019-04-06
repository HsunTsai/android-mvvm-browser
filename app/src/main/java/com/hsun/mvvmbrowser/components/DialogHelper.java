package com.hsun.mvvmbrowser.components;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hsun.mvvmbrowser.R;

public class DialogHelper {

    private AlertDialog.Builder alertDialog;
    private Dialog dialog;

    private Context context;
    private TYPE type;
    private PositiveListener positiveListener;
    private NegativeListener negativeListener;
    private NeutralListener neutralListener;

    private String positiveText;

    public enum TYPE {
        INPUT, TEXT
    }

    public DialogHelper(Context context) {
        this.context = context;
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(context.getText(R.string.common_confirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if (null != positiveListener) positiveListener.onClick("");
                    }
                });
        alertDialog.setNegativeButton(context.getString(R.string.common_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }

    //Interface
    public interface PositiveListener {
        public void onClick(String text);
    }

    //Interface
    public interface NeutralListener {
        public void onClick();
    }

    //Interface
    public interface NegativeListener {
        public void onClick();
    }

    public DialogHelper setTitle(String title) {
        alertDialog.setTitle(title);
        return this;
    }

    public DialogHelper setDialogPositiveListener(final PositiveListener positiveListener) {
        this.positiveListener = positiveListener;
        return this;
    }

    public DialogHelper setDialogPositiveListener(String positiveText, final PositiveListener positiveListener) {
        this.positiveListener = positiveListener;
        this.positiveText = positiveText;
        alertDialog.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if (null != positiveListener) positiveListener.onClick("");
                    }
                });
        return this;
    }

    public DialogHelper setDialogNeutralListener(String neutralText, final NeutralListener neutralListener) {
        alertDialog.setNeutralButton(neutralText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                        if (null != neutralListener) neutralListener.onClick();
                    }
                });
        return this;
    }

    public DialogHelper setType(TYPE type) {
        CharSequence positive_txt = (null == positiveText ? context.getText(R.string.common_confirm) : positiveText);
        this.type = type;
        switch (type) {
            case INPUT:
                final EditText editText = new EditText(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(lp);
                editText.setPadding(getDp(24), getDp(16), getDp(24), 0);
                editText.setBackground(null);
                //editText.setGravity(Gravity.CENTER);
                editText.setSingleLine();
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
                editText.setText("https://");
                editText.setSelection(editText.getText().length());
                alertDialog.setView(editText);
                alertDialog.setPositiveButton(positive_txt,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();
                                if (null != positiveListener) {
                                    String text = editText.getText().toString();
                                    positiveListener.onClick(text);
                                }
                            }
                        }
                );
                break;

            case TEXT:

                break;
        }
        return this;
    }

    private int getDp(int value) {
        Resources r = context.getResources();
        return ((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                r.getDisplayMetrics()));
    }

    public DialogHelper show() {
        if (null == dialog) {
            dialog = alertDialog.create();

            if (null != dialog.getWindow() && type == TYPE.INPUT)
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            dialog.show();
        } else {
            if (!dialog.isShowing()) dialog.show();
        }
        return this;
    }
}
