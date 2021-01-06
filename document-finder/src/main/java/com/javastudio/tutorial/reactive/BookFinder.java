package com.javastudio.tutorial.reactive;

import com.javastudio.tutorial.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SubmissionPublisher;

public class BookFinder {
    public static final Logger LOGGER = LoggerFactory.getLogger(BookFinder.class);

    public void start(String directory) {
        SubmissionPublisher<Book> publisher = new SubmissionPublisher<>();
        publisher.subscribe(new BookContentManager());

        // Search directory and submit pdf files
        Book book = Book.builder().name("null").author("null").isbn(null).build();
        publisher.submit(book);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            LOGGER.warn(e.getMessage(), e);
        }

        publisher.close();
    }
}
