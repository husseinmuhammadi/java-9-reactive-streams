package com.javastudio.reactive.flow;

import com.javastudio.reactive.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class StockSubscriber implements Flow.Subscriber<Stock> {

    private Logger logger = LoggerFactory.getLogger(StockSubscriber.class);

    private Flow.Subscription subscription;

    public void processBatch(long n) {
        this.subscription.request(n);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(Stock item) {
        logger.info("<<< processing {}", item);
        try {
            TimeUnit.MICROSECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("<<< processed {}", item);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error(throwable.getLocalizedMessage(), throwable);
    }

    @Override
    public void onComplete() {
        logger.info("Completed!");
    }
}
