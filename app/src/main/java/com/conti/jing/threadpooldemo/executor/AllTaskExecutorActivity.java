package com.conti.jing.threadpooldemo.executor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.conti.jing.threadpooldemo.MainActivity;
import com.conti.jing.threadpooldemo.R;
import com.conti.jing.threadpooldemo.view.ProgressListAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllTaskExecutorActivity extends Activity {
    private static ExecutorService sAllTaskExecutorService;
    private TextView mTaskTitleTextView;
    private ListView mTaskProgressListView;

    static {
        sAllTaskExecutorService = Executors.newCachedThreadPool();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_task);
        initView();
    }

    private void initView() {
        mTaskTitleTextView = (TextView) findViewById(R.id.textview_task_title);
        mTaskTitleTextView.setText("All Task Executor Mode");
        mTaskProgressListView = (ListView) findViewById(R.id.listview_task_progress);
        ProgressListAdapter progressListAdapter = new ProgressListAdapter(this, MainActivity.TASK_TOTAL_NUM, sAllTaskExecutorService);
        mTaskProgressListView.setAdapter(progressListAdapter);
    }
}
