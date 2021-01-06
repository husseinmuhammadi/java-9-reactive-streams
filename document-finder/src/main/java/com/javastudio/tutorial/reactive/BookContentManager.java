package com.javastudio.tutorial.reactive;

import com.javastudio.tutorial.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

public class BookContentManager implements Flow.Subscriber<Book> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookContentManager.class);

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Book item) {
        LOGGER.info("{}-{}-{}", item.getName(), item.getAuthor(), item.getIsbn());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
