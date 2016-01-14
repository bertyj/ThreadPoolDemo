package com.conti.jing.threadpooldemo;

import android.os.AsyncTask;
import android.os.SystemClock;

public class ProcessItemAsyncTask extends AsyncTask<Void, Integer, Void> {
    private ProcessItemView mProcessItemView;
    private StringBuffer mItemTitle;
    private int mItemIndex;

    public ProcessItemAsyncTask(ProcessItemView processItemView) {
        this.mProcessItemView = processItemView;
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
        mProcessItemView.setItemContent(values[0]);
    }

    @Override
    protected void onPreExecute() {
        mProcessItemView.setItemTitle(mItemTitle.toString());
    }

}
