package com.conti.jing.threadpooldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.conti.jing.threadpooldemo.executor.AllTaskExecutorActivity;
import com.conti.jing.threadpooldemo.executor.LimitedTaskExecutorActivity;
import com.conti.jing.threadpooldemo.executor.ScheduledTaskExecutorActivity;
import com.conti.jing.threadpooldemo.executor.ScheduledTaskFactoryExecutorActivity;
import com.conti.jing.threadpooldemo.executor.SingleTaskExecutorActivity;
import com.conti.jing.threadpooldemo.view.ProgressItemAsyncTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mSingleTaskButton;
    private Button mLimitedTaskButton;
    private Button mAllTaskButton;
    private Button mScheduledTaskButton;
    private Button mScheduledTaskFactoryButton;
    public static ExecutorService sSingleTaskExecutorService;
    public static ExecutorService sLimitedTaskExecutorService;
    public static ExecutorService sAllTaskExecutorService;
    public static ExecutorService sScheduledTaskExecutorService;
    public static ExecutorService sScheduledTaskFactoryExecutorService;
    public static final int TASK_TOTAL_NUM = Runtime.getRuntime().availableProcessors() * 2;
    public static boolean sTaskIsCanceled;
    public static List<ProgressItemAsyncTask> sTaskList;
    public static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();

    static {
        sSingleTaskExecutorService = Executors.newSingleThreadExecutor();
        sLimitedTaskExecutorService = Executors.newFixedThreadPool(3);
        sAllTaskExecutorService = Executors.newCachedThreadPool();
        sScheduledTaskExecutorService = Executors.newScheduledThreadPool(3);
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
        setContentView(R.layout.layout_main_activity);
        initView();
    }

    private void initView() {
        mSingleTaskButton = (Button) findViewById(R.id.button_single_task);
        mSingleTaskButton.setOnClickListener(this);
        mLimitedTaskButton = (Button) findViewById(R.id.button_limited_task);
        mLimitedTaskButton.setOnClickListener(this);
        mAllTaskButton = (Button) findViewById(R.id.button_all_task);
        mAllTaskButton.setOnClickListener(this);
        mScheduledTaskButton = (Button) findViewById(R.id.button_scheduled_task);
        mScheduledTaskButton.setOnClickListener(this);
        mScheduledTaskFactoryButton = (Button) findViewById(R.id.button_scheduled_task_factory);
        mScheduledTaskFactoryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_single_task:
                startActivity(new Intent(this, SingleTaskExecutorActivity.class));
                break;
            case R.id.button_limited_task:
                startActivity(new Intent(this, LimitedTaskExecutorActivity.class));
                break;
            case R.id.button_all_task:
                startActivity(new Intent(this, AllTaskExecutorActivity.class));
                break;
            case R.id.button_scheduled_task:
                startActivity(new Intent(this, ScheduledTaskExecutorActivity.class));
                break;
            case R.id.button_scheduled_task_factory:
                startActivity(new Intent(this, ScheduledTaskFactoryExecutorActivity.class));
                break;
        }
    }
}
