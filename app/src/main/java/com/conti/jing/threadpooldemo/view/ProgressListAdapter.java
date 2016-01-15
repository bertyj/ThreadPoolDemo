package com.conti.jing.threadpooldemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.conti.jing.threadpooldemo.MainActivity;
import com.conti.jing.threadpooldemo.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ProgressListAdapter extends BaseAdapter {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final int mTaskTotalNum;
    private final ExecutorService mTaskExecutorService;

    public ProgressListAdapter(Context context, int taskTotalNum, ExecutorService executorService) {
        this.mContext = context;
        this.mTaskTotalNum = taskTotalNum;
        this.mTaskExecutorService = executorService;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        MainActivity.sTaskList = new ArrayList<ProgressItemAsyncTask>(mTaskTotalNum);
    }

    @Override
    public int getCount() {
        return mTaskTotalNum;
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.sTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.layout_progress_item, null);
            ProgressItemAsyncTask progressItemAsyncTask = new ProgressItemAsyncTask((ProgressItemView) convertView);
            progressItemAsyncTask.executeOnExecutor(mTaskExecutorService);
            MainActivity.sTaskList.add(progressItemAsyncTask);
        }
        return convertView;
    }
}
