package com.javastudio.tutorial.reactive;

import com.javastudio.tutorial.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Flow;

public class MySubscriber implements Flow.Subscriber<Product> {
    Logger logger = LoggerFactory.getLogger(MySubscriber.class);

    private int counter = 0;
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        logger.info("Subscribed");
        this.subscription = subscription;
        subscription.request(1);
        logger.info("onSubscribe requested 1 item");
    }

    @Override
    public void onNext(Product item) {
        logger.info("Product {}-{}", item.getId(), item.getName());
        try {
            Thread.sleep(new Random().nextInt(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        counter++;
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
    }

    @Override
    public void onComplete() {
        logger.info("All Processing Done");
    }

    public int getCounter() {
        return counter;
    }
}
