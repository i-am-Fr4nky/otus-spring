package ru.otus.iamfranky.homework.books.dao;

import ru.otus.iamfranky.homework.books.domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();
}
