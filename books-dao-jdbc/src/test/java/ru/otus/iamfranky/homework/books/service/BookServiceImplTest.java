package ru.otus.iamfranky.homework.books.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.iamfranky.homework.books.dao.BooksDao;
import ru.otus.iamfranky.homework.books.domain.Book;
import ru.otus.iamfranky.homework.books.domain.Genre;
import ru.otus.iamfranky.homework.books.exception.BookNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.iamfranky.homework.books.fixtures.BookFixtures.*;

@DisplayName("Методы сервиса работы с книгами должны ")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @MockBean
    private BooksDao booksDao;

    @Autowired
    private BookServiceImpl bookService;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    private final RuntimeException expectedException = new RuntimeException("Exception");


    // ------------------
    // ----- getAll -----
    // ------------------

    @Test
    @DisplayName("вызвать метод DAO для получения всех книг и возвращать результат")
    void shouldGetAll() throws BookNotFoundException {
        when(booksDao.getAll()).thenReturn(BOOKS);
        var result = bookService.getAll();
        assertEquals(BOOKS, result);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений получении всех книг")
    void shouldGetAllThrowsBookNotFoundException() throws BookNotFoundException {
        when(booksDao.getAll()).thenReturn(List.of());
        var actualException = assertThrows(BookNotFoundException.class, () -> bookService.getAll());
        assertEquals("Error: books not found", actualException.getMessage());
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений получении всех книг")
    void shouldGetAllThrowsAllExceptions() throws BookNotFoundException {
        when(booksDao.getAll()).thenThrow(expectedException);
        var actualException = assertThrows(RuntimeException.class, () -> bookService.getAll());
        assertEquals(expectedException, actualException);
    }


    // ------------------------
    // ----- SearchByName -----
    // ------------------------

    @Test
    @DisplayName("вызвать метод DAO для поиска книг по имени и возвращать результат")
    void shouldSearchByName() throws BookNotFoundException {
        when(booksDao.searchByName(BOOK_NAME)).thenReturn(BOOKS);
        var result = bookService.searchByName(BOOK_NAME);
        assertEquals(BOOKS, result);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений при поиске книг по имени")
    void shouldSearchByNameThrowsAllExceptions() throws BookNotFoundException {
        when(booksDao.searchByName(BOOK_NAME)).thenThrow(expectedException);
        var actualException = assertThrows(RuntimeException.class, () -> bookService.searchByName(BOOK_NAME));
        assertEquals(expectedException, actualException);
    }


    // ---------------
    // ----- get -----
    // ---------------

    @Test
    @DisplayName("вызвать метод DAO для получения книги по id и возвращать результат")
    void shouldGetById() throws BookNotFoundException {
        when(booksDao.get(BOOK_ID)).thenReturn(BOOK_1);
        var result = bookService.get(BOOK_ID);
        assertEquals(BOOK_1, result);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений при получениb книги по id")
    void shouldGetByIdThrowsAllExceptions() throws BookNotFoundException {
        when(booksDao.get(BOOK_ID)).thenThrow(expectedException);
        var actualException = assertThrows(RuntimeException.class, () -> bookService.get(BOOK_ID));
        assertEquals(expectedException, actualException);
    }


    // ------------------
    // ----- delete -----
    // ------------------

    @Test
    @DisplayName("вызвать метод DAO для удаления книги по id")
    void shouldDelete() {
        bookService.delete(BOOK_ID);
        verify(booksDao).delete(BOOK_ID);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений при удалении книги по id")
    void shouldDeleteThrowsAllExceptions() {
        willThrow(expectedException).given(booksDao).delete(BOOK_ID);
        var actualException = assertThrows(RuntimeException.class, () -> booksDao.delete(BOOK_ID));
        assertEquals(expectedException, actualException);
    }


    // ------------------
    // ----- add -----
    // ------------------

    @Test
    @DisplayName("вызвать метод DAO для добавление книги, передать заданные параметры и возвращать результат")
    void shouldAdd() {
        when(booksDao.insert(bookCaptor.capture())).thenReturn(BOOK_1);
        var insertingBook = bookService.add(AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS);
        assertEquals(insertingBook, BOOK_1);
        checkBook(bookCaptor.getValue(), 0, AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений при добавлении книги")
    void shouldAddThrowsAllExceptions()  {
        when(booksDao.insert(any(Book.class))).thenThrow(expectedException);
        var actualException = assertThrows(RuntimeException.class, () ->
                bookService.add(AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS));
        assertEquals(expectedException, actualException);
    }


    // ------------------
    // ----- update -----
    // ------------------

    @Test
    @DisplayName("вызвать метод DAO для обновления книги и передать заданные параметры")
    void shouldUpdateAdd() {
        bookService.update(BOOK_ID, AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS);
        verify(booksDao).update(bookCaptor.capture());
        checkBook(bookCaptor.getValue(), BOOK_ID, AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений при обновлении книги")
    void shouldUpdateThrowsAllExceptions()  {
        willThrow(expectedException).given(booksDao).update(any(Book.class));
        var actualException = assertThrows(RuntimeException.class, () ->
                bookService.update(BOOK_ID, AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS));
        assertEquals(expectedException, actualException);
    }


    private void checkBook(Book book, int bookId, int authorId, String name, String desc, int[] genreIds) {
        assertEquals(bookId, book.getId());
        assertEquals(authorId, book.getAuthor().getId());
        assertEquals(name, book.getName());
        assertEquals(desc, book.getDesc());
        assertThat(book.getGenres())
                .hasSize(genreIds.length)
                .extracting(Genre::getId)
                .containsAll(Arrays.stream(genreIds).boxed().collect(Collectors.toList()));
    }
}