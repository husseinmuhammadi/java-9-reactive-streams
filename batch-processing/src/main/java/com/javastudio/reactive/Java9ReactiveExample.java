package com.javastudio.reactive;

import com.javastudio.reactive.configuration.AsyncConfiguration;
import com.javastudio.reactive.flow.StockSubscriber;
import com.javastudio.reactive.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Java9ReactiveExample {

    private Logger logger = LoggerFactory.getLogger(Java9ReactiveExample.class);

    public void demo() throws InterruptedException {
        AsyncConfiguration.INSTANCE.init();
        SubmissionPublisher<Stock> publisher = new SubmissionPublisher<>(AsyncConfiguration.INSTANCE.getExecutorService(), 20000);

        var subscriber = new StockSubscriber();
        publisher.subscribe(subscriber);

        AsyncConfiguration.INSTANCE.getExecutorService().submit(() -> {
            IntStream.range(1, 10_000_000).mapToObj(i -> new Stock(generateRandomString(), i))
                    .forEach(item -> {
//                        logger.info(">>> submit {}", item);
                        publisher.submit(item);
                    });
        });


        for (int i = 0; i <10; i++) {
            logger.info(" --- batch {} ---", 3);
            subscriber.processBatch(20);
        }

        TimeUnit.SECONDS.sleep(10);
    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
