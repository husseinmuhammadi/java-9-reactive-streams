package com.javastudio.tutorial.reactive;

import com.javastudio.tutorial.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SubmissionPublisher;

public class ReactiveDemo {
    private Logger logger = LoggerFactory.getLogger(ReactiveDemo.class);
    ProductHelper productHelper = new ProductHelper();

    public void simulate() throws InterruptedException {
        SubmissionPublisher<Product> publisher = new SubmissionPublisher<>();

        MySubscriber subscriber = new MySubscriber();
        publisher.subscribe(subscriber);

        logger.info("Publishing Items to Subscriber");
        productHelper.process("people.txt", publisher::submit);

        // logic to wait till processing of all messages are over
        while (productHelper.getCount() != subscriber.getCounter()) {
            Thread.sleep(10);
        }

        // close the Publisher
        logger.info("The publisher is about to be closed!");
        publisher.close();

        logger.info("Exiting the app");
    }
}
