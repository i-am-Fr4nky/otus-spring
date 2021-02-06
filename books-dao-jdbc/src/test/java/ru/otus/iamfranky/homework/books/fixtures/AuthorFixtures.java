package ru.otus.iamfranky.homework.books.fixtures;

import ru.otus.iamfranky.homework.books.domain.Author;

import java.util.List;

public class AuthorFixtures {

    public static final Author AUTHOR_1 = new Author(1, "name1", "surname1", "middleName1");
    public static final Author AUTHOR_2 = new Author(2, "name2", "surname2", "middleName2");

    public static final List<Author> AUTHORS = List.of(AUTHOR_1, AUTHOR_2);
}