package com.mahendra.serviceworker.service;

import android.os.Process;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorSupplier {
    private final int NUM_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final ThreadPoolExecutor backgroundExecutor;
    private final BlockingDeque<Runnable> mBlockingQueue;

    private static ExecutorSupplier supplier;
    public static ExecutorSupplier getInstance(){
        synchronized (ExecutorSupplier.class){
            if(supplier == null){
                supplier = new ExecutorSupplier();
            }
        }
        return supplier;
    }

    private ExecutorSupplier() {
        mBlockingQueue = new LinkedBlockingDeque<Runnable>();
        backgroundExecutor = new ThreadPoolExecutor(
                NUM_OF_CORES * 2,
                NUM_OF_CORES * 3,
                30,
                TimeUnit.SECONDS,
                mBlockingQueue,
                new PThreadFactory(Process.THREAD_PRIORITY_BACKGROUND)

        );
    }

    ThreadPoolExecutor getBackgroundExecutor(){
        return backgroundExecutor;
    }
}
