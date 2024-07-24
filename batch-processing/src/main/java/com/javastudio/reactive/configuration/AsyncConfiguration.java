package com.javastudio.reactive.configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum AsyncConfiguration {
    INSTANCE;

    private static final int EXECUTOR_ASYNC_POOL_SIZE = 4;
    private static final int EXECUTOR_ASYNC_POOL_SIZE_MAX = 5;
    private static final long EXECUTOR_ASYNC_POOL_KEEP_ALIVE = 2;
    private static final int EXECUTOR_ASYNC_QUEUE_CAPACITY = 25;

    private ExecutorService executorService;

    public synchronized void init() {
        executorService = new ThreadPoolExecutor(
                EXECUTOR_ASYNC_POOL_SIZE,
                EXECUTOR_ASYNC_POOL_SIZE_MAX,
                EXECUTOR_ASYNC_POOL_KEEP_ALIVE,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(EXECUTOR_ASYNC_QUEUE_CAPACITY, true),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
