package ru.otus.iamfranky.homework.books.dao;

import ru.otus.iamfranky.homework.books.domain.Book;
import ru.otus.iamfranky.homework.books.exception.BookNotFoundException;

import java.util.List;

public interface BooksDao {

    Book insert(Book book);
    void update(Book book);

    Book get(int id) throws BookNotFoundException;
    void delete(int id);

    List<Book> getAll() throws BookNotFoundException;
    List<Book> searchByName(String name) throws BookNotFoundException;

}
