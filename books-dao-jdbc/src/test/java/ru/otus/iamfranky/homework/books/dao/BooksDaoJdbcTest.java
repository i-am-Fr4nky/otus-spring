package ru.otus.iamfranky.homework.books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.iamfranky.homework.books.domain.Author;
import ru.otus.iamfranky.homework.books.domain.Book;
import ru.otus.iamfranky.homework.books.domain.Genre;
import ru.otus.iamfranky.homework.books.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Методы Dao для работы с книгами должны ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BooksDaoJdbc.class)
class BooksDaoJdbcTest {

    @Autowired
    private BooksDaoJdbc booksDaoJdbc;

    private static final String PREFIX_FOR_LIKE_SEARCH = " withPrefixForLikeSearch";
    private static final String NOT_IN_NAME_EXPRESSION = "notIn";

    private static final int NOT_EXISTING_BOOK_ID = 100;

    private List<Book> dbBookLocalCopy = new ArrayList() {{
        add(new Book(1, "book 1 name" + PREFIX_FOR_LIKE_SEARCH, "book 1 desc", Author.forParams(1), Set.of(Genre.forParams(1))));
        add(new Book(2, "book 2 name" + PREFIX_FOR_LIKE_SEARCH, "book 2 desc", Author.forParams(2), Set.of(Genre.forParams(2))));
        add(new Book(3, "book 3 name", "book 3 desc", Author.forParams(2), Set.of(Genre.forParams(2))));
    }};

    @Test
    @DisplayName("Добавлять книгу")
    void shouldInsert() {
        var genres = Set.of(Genre.forParams(1), Genre.forParams(2));
        var insertingBook = Book.forParams("new name", "new desc", Author.forParams(1), genres);

        var newBook = booksDaoJdbc.insert(insertingBook);
        assertThat(newBook.getId()).isGreaterThan(0);
        assertBooks(insertingBook, newBook);
    }

    @Test
    @DisplayName("Обновлять данные книги")
    void update() throws BookNotFoundException {
        var genres = Set.of(Genre.forParams(1), Genre.forParams(2));
        var book = new Book(2, "new name", "new desc", Author.forParams(1), genres);

        booksDaoJdbc.update(book);
        var updatedBook = booksDaoJdbc.get(book.getId());
        assertBooks(book, updatedBook);
    }

    @Test
    @DisplayName("Получать данные книги из БД по идентификатору")
    void get() throws BookNotFoundException {
        var existingBookId = 2;
        var testBookIndex = 1;

        var book = booksDaoJdbc.get(existingBookId);
        assertBooks(dbBookLocalCopy.get(testBookIndex), book);
    }

    @Test
    @DisplayName("Бросать исключение, если книга не напйдена")
    void shouldThrowIfBookNotExist() {
        assertThrows(BookNotFoundException.class, () -> booksDaoJdbc.get(NOT_EXISTING_BOOK_ID));
    }


    @Test
    @DisplayName("Удалять книгу из БД по идентификатору")
    void delete() throws BookNotFoundException {
        var delBook = dbBookLocalCopy.get(2);
        dbBookLocalCopy.remove(delBook);

        booksDaoJdbc.delete(delBook.getId());
        var result = booksDaoJdbc.getAll();
        assertThat(result)
                .hasSize(dbBookLocalCopy.size())
                .containsAll(dbBookLocalCopy);
    }

    @Test
    @DisplayName("Получать все хранящиеся книги")
    void shouldGetAll() throws BookNotFoundException {
        var result = booksDaoJdbc.getAll();
        assertThat(result)
                .hasSize(dbBookLocalCopy.size())
                .containsAll(dbBookLocalCopy);
    }

    @Test
    @DisplayName("Бросать исключение если книг нет")
    void shouldThrowIfBooksNotExist() {
        // delete all book from DB
        dbBookLocalCopy.forEach(b ->
                booksDaoJdbc.delete(b.getId()));
        // try getAll
        assertThrows(BookNotFoundException.class, () -> booksDaoJdbc.getAll());
    }

    @Test
    @DisplayName("Искать книгу по конкретному имени")
    void shouldByConcreteName() throws BookNotFoundException {
        var lookingBook = dbBookLocalCopy.get(1);
        var foundingBooks = booksDaoJdbc.searchByName(lookingBook.getName());

        assertThat(foundingBooks).hasSize(1);
        assertBooks(lookingBook, foundingBooks.get(0));
    }

    @Test
    @DisplayName("Искать книги по частичному соотвествию в имени")
    void shouldSearchByPrefix() throws BookNotFoundException {
        var foundingBooks = booksDaoJdbc.searchByName(PREFIX_FOR_LIKE_SEARCH);
        assertThat(foundingBooks)
                .hasSize(2)
                .contains(dbBookLocalCopy.get(0))
                .contains(dbBookLocalCopy.get(1));
    }

    @Test
    @DisplayName("Бросать исключение если книга с нужным именем не найдена")
    void shouldThrowIfNotFinding() {
        assertThrows(BookNotFoundException.class, () -> booksDaoJdbc.searchByName(NOT_IN_NAME_EXPRESSION));
    }


    private void assertBooks(Book expected, Book actual) {
        var actualGenresIds = actual.getGenres().stream()
                .map(Genre::getId)
                .collect(Collectors.toSet());

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDesc(), actual.getDesc());
        assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
        assertThat(expected.getGenres())
                .hasSize(expected.getGenres().size())
                .extracting(Genre::getId)
                .containsAll(actualGenresIds);
    }
}