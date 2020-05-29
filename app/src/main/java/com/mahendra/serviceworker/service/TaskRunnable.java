package com.mahendra.serviceworker.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.mahendra.serviceworker.service.Task;
import com.mahendra.serviceworker.service.TaskHandler;

public class TaskRunnable<T> implements Runnable {
    private Task<T> task;
    private TaskHandler<T> taskHandler;
    public TaskRunnable(Task<T> task){
        this.task = task;
        taskHandler = new TaskHandler<>(task,Looper.getMainLooper());
    }
    @Override
    public void run() {
        T result = task.onExecuteTask();
        Message message = new Message();
        message.obj = result;
        taskHandler.dispatchMessage(message);

    }
}
