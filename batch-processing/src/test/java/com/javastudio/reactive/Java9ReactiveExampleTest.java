package com.javastudio.reactive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Java9ReactiveExampleTest {

    @InjectMocks
    private Java9ReactiveExample example;

    @Test
    void name() throws InterruptedException {
        assertNotNull(example);

        example.demo();
    }
}