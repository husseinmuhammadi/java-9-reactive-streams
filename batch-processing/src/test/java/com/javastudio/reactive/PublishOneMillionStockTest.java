package com.javastudio.reactive;

import com.javastudio.reactive.configuration.AsyncConfiguration;
import com.javastudio.reactive.flow.StockSubscriber;
import com.javastudio.reactive.model.Stock;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class PublishOneMillionStockTest {

    private Flow.Publisher<Stock> publisher;
    private Flow.Subscriber<Stock> subscriber;

    @BeforeEach
    void setUp() {
        publisher = new SubmissionPublisher<>(AsyncConfiguration.INSTANCE.getExecutorService(), 100);
        subscriber = new StockSubscriber();
        publisher.subscribe(subscriber);


        ((SubmissionPublisher<Stock>) publisher).submit(createRandomStock());
    }

    private Stock createRandomStock() {
        return new Stock(generateRandomString(), 1000);
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
