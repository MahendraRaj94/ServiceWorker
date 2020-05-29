package com.mahendra.serviceworker.service;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

public interface Task<T> {
    @WorkerThread
    T onExecuteTask();
    @MainThread
    void onTaskComplete(T t);
}
