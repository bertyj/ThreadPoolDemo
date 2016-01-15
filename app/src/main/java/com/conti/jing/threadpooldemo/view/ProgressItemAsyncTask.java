package com.conti.jing.threadpooldemo.view;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.conti.jing.threadpooldemo.MainActivity;

public class ProgressItemAsyncTask extends AsyncTask<Void, Integer, Void> {
    private ProgressItemView mProgressItemView;
    private StringBuffer mItemTitle;
    private static int mItemIndex;

    public ProgressItemAsyncTask(ProgressItemView progressItemView) {
        this.mProgressItemView = progressItemView;
        this.mItemTitle = new StringBuffer("执行任务：");
        if (mItemIndex > MainActivity.TASK_TOTAL_NUM) {
            mItemIndex = 0;
            mItemTitle.append(mItemIndex++);
        } else {
            mItemTitle.append(mItemIndex++);
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (!isCancelled() && !MainActivity.sTaskIsCanceled) {
            int progressValue = 0;
            while (progressValue < 101) {
                if (progressValue < 70) {
                    SystemClock.sleep(100);
                } else {
                    SystemClock.sleep(200);
                }
                publishProgress(progressValue);
                progressValue++;
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressItemView.setItemContent(values[0]);
    }

    @Override
    protected void onPreExecute() {
        mProgressItemView.setItemTitle(mItemTitle.toString());
    }

}
