package ru.otus.iamfranky.homework.books.exception;

import lombok.Getter;

@Getter
public class BookNotFoundException extends Exception {

    private static final String MSG_BOOK_ID = "Error: book with id: %d not found";
    private static final String MSG_BOOK_NAME = "Book with pattern name: %s not found";
    private static final String MSG_BOOKS = "Error: books not found";

    public BookNotFoundException(int bookId) {
        super(String.format(MSG_BOOK_ID, bookId));
    }

    public BookNotFoundException(String name) {
        super(String.format(MSG_BOOK_NAME, name));
    }

    public BookNotFoundException() {
        super(MSG_BOOKS);
    }
}
