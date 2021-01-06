package com.javastudio.tutorial;

import com.javastudio.tutorial.reactive.BookFinder;

import java.awt.print.Book;

public class Application {
    public static void main(String[] args) {
        BookFinder  bookFinder=new BookFinder();
        bookFinder.start("C:\\");
    }
}
