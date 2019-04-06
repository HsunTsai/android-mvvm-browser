package com.hsun.mvvmbrowser.activities.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class HintAdapter extends ArrayAdapter<String> {

    public HintAdapter(Context context, List<String> objects, int id) {
        super(context, id, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}