package ru.otus.iamfranky.homework.books.fixtures;

import ru.otus.iamfranky.homework.books.domain.Book;

import java.util.List;

public class BookFixtures {

    public static final Book BOOK_1 = new Book(1, "book1", "This is book 1", AuthorFixtures.AUTHOR_1,
            GenreFixtures.GENRES_SET);
    public static final Book BOOK_2 = new Book(2, "book2", "This is book 2", AuthorFixtures.AUTHOR_2,
            GenreFixtures.GENRES_SET);

    public static final List<Book> BOOKS = List.of(BOOK_1, BOOK_2);

    public static final int BOOK_ID = 1;
    public static final int AUTHOR_ID = 11;
    public static final String BOOK_NAME = "book name";
    public static final String BOOK_DESC = "book desc";
    public static final int[] BOOK_GENRE_IDS = {5, 4, 3, 2, 0};
}