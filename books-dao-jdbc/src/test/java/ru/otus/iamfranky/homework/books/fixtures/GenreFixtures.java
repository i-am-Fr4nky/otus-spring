package ru.otus.iamfranky.homework.books.fixtures;

import ru.otus.iamfranky.homework.books.domain.Genre;

import java.util.List;
import java.util.Set;

public class GenreFixtures {

    public static final Genre GENRE_1 = new Genre(11, "genre 1");
    public static final Genre GENRE_2 = new Genre(22, "genre 2");

    public static final List<Genre> GENRES_LIST = List.of(GENRE_1, GENRE_2);
    public static final Set<Genre> GENRES_SET = Set.of(GENRE_1, GENRE_2);
}