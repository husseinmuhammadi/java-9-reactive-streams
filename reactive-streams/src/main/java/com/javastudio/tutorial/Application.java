package com.javastudio.tutorial;

import com.javastudio.tutorial.reactive.ReactiveDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Hello world!");

        try {
            ReactiveDemo reactiveDemo = new ReactiveDemo();
            reactiveDemo.simulate();
        } catch (InterruptedException e) {
            logger.info(e.getMessage(), e);
        }
    }
}
