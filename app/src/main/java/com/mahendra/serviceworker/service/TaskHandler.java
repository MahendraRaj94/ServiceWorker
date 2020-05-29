package com.mahendra.serviceworker.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mahendra.serviceworker.service.Task;

public class TaskHandler<T> extends Handler {
    private final Task<T> task;
    private final Looper looper;
    public TaskHandler(Task<T> task,Looper looper){
        super(looper);
        this.looper = looper;
        this.task = task;
    }

    @Override
    public void handleMessage(final @NonNull Message msg) {
        super.handleMessage(msg);
        post(new Runnable() {
            @Override
            public void run() {
                task.onTaskComplete((T) msg.obj);
            }
        });
        Log.d("Task","Task Completed " + msg.obj.toString());

    }
}
