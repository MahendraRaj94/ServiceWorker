package com.mahendra.serviceworker.service;

import android.util.Log;

public class ServiceWorker<T> {
    private String workerName;
    private ExecutorSupplier supplier;

    public ServiceWorker(String workerName) {
        this.workerName = workerName;
        supplier = ExecutorSupplier.getInstance();
    }

    public void add(final Task<T> task){
       supplier.getBackgroundExecutor().execute(new TaskRunnable<T>(task));
        Log.d("Task","Task Added");
    }
}
