package com.javastudio.tutorial.model;

public class Book {
    private final String name;
    private final String author;
    private final String isbn;

    private Book(BookBuilder builder) {
        this.name = builder.name;
        this.author = builder.author;
        this.isbn = builder.isbn;

    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {
        private String name;
        private String author;
        private String isbn;

        public BookBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            Book book = new Book(this);
            validateBookObject(book);
            return book;
        }

        private void validateBookObject(Book book) {

        }
    }
}
