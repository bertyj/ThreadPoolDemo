package com.conti.jing.threadpooldemo.executor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.conti.jing.threadpooldemo.MainActivity;
import com.conti.jing.threadpooldemo.R;
import com.conti.jing.threadpooldemo.view.ProgressListAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ScheduledTaskFactoryExecutorActivity extends Activity {
    private static ExecutorService sScheduledTaskFactoryExecutorService;
    private TextView mTaskTitleTextView;
    private ListView mTaskProgressListView;

    static {
        sScheduledTaskFactoryExecutorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("Scheduled_Task_Factory_Thread");
                thread.setDaemon(true);
                return thread;
            }
        });
        sScheduledTaskFactoryExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Log.i("Running Log", "Scheduled_Task_Factory_Thread Submit Running");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_task);
        initView();
    }

    private void initView() {
        mTaskTitleTextView = (TextView) findViewById(R.id.textview_task_title);
        mTaskTitleTextView.setText("Scheduled Task Factory Executor Mode");
        mTaskProgressListView = (ListView) findViewById(R.id.listview_task_progress);
        ProgressListAdapter progressListAdapter = new ProgressListAdapter(this, MainActivity.TASK_TOTAL_NUM, sScheduledTaskFactoryExecutorService);
        mTaskProgressListView.setAdapter(progressListAdapter);
    }

}
