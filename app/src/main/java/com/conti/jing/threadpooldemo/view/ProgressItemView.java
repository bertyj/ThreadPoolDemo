package com.conti.jing.threadpooldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.conti.jing.threadpooldemo.R;

public class ProgressItemView extends LinearLayout {
    private TextView mItemTitleTextView;
    private ProgressBar mItemContentProgressBar;

    public ProgressItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setItemTitle(String title) {
        if (mItemTitleTextView == null) {
            mItemTitleTextView = (TextView) findViewById(R.id.textview_item_title);
        }
        mItemTitleTextView.setText(title);
    }

    public void setItemContent(int progress) {
        if (mItemContentProgressBar == null) {
            mItemContentProgressBar = (ProgressBar) findViewById(R.id.progressbar_item_content);
        }
        mItemContentProgressBar.setProgress(progress);
    }
}
