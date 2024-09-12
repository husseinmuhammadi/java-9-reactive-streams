package com.javastudio.tutorial;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ReactiveStreamTest {
    public static final int NUMBER_OF_ITEMS = 100000;
    public static final int BATCH_SIZE = NUMBER_OF_ITEMS / 100;

    private static final int EXECUTOR_ASYNC_POOL_SIZE = 4;
    private static final int EXECUTOR_ASYNC_POOL_SIZE_MAX = 5;
    private static final long EXECUTOR_ASYNC_POOL_KEEP_ALIVE = 2;
    private static final int EXECUTOR_ASYNC_QUEUE_CAPACITY = 25;

    private final Logger logger = LoggerFactory.getLogger(ReactiveStreamTest.class.getName());

    ExecutorService executorService() {
        return new ThreadPoolExecutor(
                EXECUTOR_ASYNC_POOL_SIZE,
                EXECUTOR_ASYNC_POOL_SIZE_MAX,
                EXECUTOR_ASYNC_POOL_KEEP_ALIVE,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(EXECUTOR_ASYNC_QUEUE_CAPACITY, true),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Test
    public void test() {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>(
                Executors.newFixedThreadPool(40), Flow.defaultBufferSize());

        Flow.Subscriber<Integer> subscriber = new NumberProcessor();

        publisher.subscribe(subscriber);

        // rang of 1000 numbers
        IntStream.rangeClosed(1, NUMBER_OF_ITEMS).forEach(i -> {
            publisher.submit(i);
        });

        publisher.close();

        logger.info("Exiting the app");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class NumberProcessor implements Flow.Subscriber<Integer> {

    public static final Set<String> threadNames = new HashSet<>();
    public static final Object lock = new Object();

    private final Logger logger = LoggerFactory.getLogger(NumberProcessor.class.getName());

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        if (item % ReactiveStreamTest.BATCH_SIZE == 0) {
            int percentage = item / ReactiveStreamTest.BATCH_SIZE;
            logger.info("{}%", percentage);
        }

        synchronized (lock) {
            threadNames.add(Thread.currentThread().getName());
        }

//        if (item == 7)
//            throw new RuntimeException("Something went wrong");
        subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        logger.error("Error: {}", e.getMessage(), e);
    }

    @Override
    public void onComplete() {
        logger.info("All Processing Done");
        for (String threadName : threadNames) {
            logger.info("Thread: {}", threadName);
        }
    }
}


