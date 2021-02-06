package ru.otus.iamfranky.homework.books.dao;

import ru.otus.iamfranky.homework.books.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();
}
