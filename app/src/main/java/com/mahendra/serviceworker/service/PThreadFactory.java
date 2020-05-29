package com.mahendra.serviceworker.service;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

public class PThreadFactory implements ThreadFactory {

    private int priority ;
    public PThreadFactory(int priority){
        this.priority = priority;
    }
    @Override
    public Thread newThread(final Runnable r) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Process.setThreadPriority(priority);
                }catch (Throwable t){

                }
                r.run();
            }
        });
    }
}
