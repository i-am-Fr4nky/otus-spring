package ru.otus.iamfranky.homework.books.service;

import ru.otus.iamfranky.homework.books.domain.Book;
import ru.otus.iamfranky.homework.books.exception.BookNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> getAll() throws BookNotFoundException;
    List<Book> searchByName(String name) throws BookNotFoundException;

    Book get(int id) throws BookNotFoundException;
    void delete(int id);

    Book add(int authorId, String name, String desc, int[] genreIds);
    void update(int id, int authorId, String name, String desc, int[] genreIds);
}
