package ru.otus.iamfranky.homework.books.shell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.iamfranky.homework.books.domain.Book;
import static ru.otus.iamfranky.homework.books.fixtures.MsgUtilsFixtures.*;
import ru.otus.iamfranky.homework.books.service.BookService;
import ru.otus.iamfranky.homework.books.utils.MsgUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.iamfranky.homework.books.fixtures.BookFixtures.*;

@DisplayName("Методы сервиса работы с книгами должны ")
@SpringBootTest(classes = BookFlowCommands.class)
class BookFlowCommandsTest {

    private static final String DELETE_MSG = "Delete success";
    private static final String UPDATE_MSG = "Update book success";
    private static final String ADDING_MSG_PTT = "Adding book success, id: %d";

    @MockBean
    private MsgUtils msgUtils;

    @MockBean
    private BookService bookService;

    @Autowired
    private BookFlowCommands bookFlowCommands;

    @Captor
    private ArgumentCaptor<List<Book>> booksCaptor;

    @Captor
    private ArgumentCaptor<MsgUtils.GetMsgFunction> getMsgFunctionArgumentCaptor;



    @BeforeEach
    void preConstruct() {
        when(msgUtils.getMsgWithSimpleExceptionHandler(getMsgFunctionArgumentCaptor.capture()))
                .thenReturn(RESPONSE_TO_LINES);
    }

    @Test
    @DisplayName("получить книги из сервиса и форматировать вывод")
    void shouldShowBooks() throws Exception {
        when(bookService.getAll()).thenReturn(BOOKS);
        when(msgUtils.listToLines(booksCaptor.capture())).thenReturn(RESPONSE_TO_LINES);

        var response = bookFlowCommands.showBooks();
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(RESPONSE_TO_LINES, getMsgFunctionArgumentCaptor.getValue().apply());
        assertEquals(BOOKS, booksCaptor.getValue());
    }

    @Test
    @DisplayName("получить книгу из сервиса и форматировать вывод")
    void getBook() throws Exception {
        when(bookService.get(BOOK_ID)).thenReturn(BOOK_1);

        var response = bookFlowCommands.getBook(BOOK_ID);
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(BOOK_1.toString(), getMsgFunctionArgumentCaptor.getValue().apply());
    }

    @Test
    @DisplayName("получить книгу из сервиса по иимени и форматировать вывод")
    void findBooks() throws Exception {
        when(bookService.searchByName(BOOK_NAME)).thenReturn(BOOKS);
        when(msgUtils.listToLines(booksCaptor.capture())).thenReturn(RESPONSE_TO_LINES);

        var response = bookFlowCommands.findBooks(BOOK_NAME);
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(RESPONSE_TO_LINES, getMsgFunctionArgumentCaptor.getValue().apply());
        assertEquals(BOOKS, booksCaptor.getValue());
    }

    @Test
    @DisplayName("удалить книгу с помощью сервиса и отобразить сообщение")
    void delete() throws Exception {
        var response = bookFlowCommands.delete(BOOK_ID);
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(DELETE_MSG, getMsgFunctionArgumentCaptor.getValue().apply());
        verify(bookService).delete(BOOK_ID);
    }

    @Test
    @DisplayName("добавить книгу с помощью сервиса и отобразить сообщение")
    void add() throws Exception {
        when(bookService.add(AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS)).thenReturn(BOOK_1);

        var response = bookFlowCommands.add(AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS);
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(String.format(ADDING_MSG_PTT, BOOK_1.getId()),
                getMsgFunctionArgumentCaptor.getValue().apply());
    }

    @Test
    @DisplayName("обновить книгу с помощью сервиса и отобразить сообщение")
    void update() throws Exception {
        var response = bookFlowCommands.update(BOOK_ID, AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS);
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(UPDATE_MSG, getMsgFunctionArgumentCaptor.getValue().apply());
        verify(bookService).update(BOOK_ID, AUTHOR_ID, BOOK_NAME, BOOK_DESC, BOOK_GENRE_IDS);
    }
}