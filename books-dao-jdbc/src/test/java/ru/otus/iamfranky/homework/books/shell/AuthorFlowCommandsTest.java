package ru.otus.iamfranky.homework.books.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.iamfranky.homework.books.domain.Author;
import static ru.otus.iamfranky.homework.books.fixtures.AuthorFixtures.*;
import static ru.otus.iamfranky.homework.books.fixtures.MsgUtilsFixtures.*;
import ru.otus.iamfranky.homework.books.service.AuthorService;
import ru.otus.iamfranky.homework.books.utils.MsgUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Методы компонента работы с авторами должны ")
@SpringBootTest(classes = AuthorFlowCommands.class)
class AuthorFlowCommandsTest {

    @MockBean
    private MsgUtils msgUtils;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private AuthorFlowCommands authorFlowCommands;

    @Captor
    private ArgumentCaptor<List<Author>> authorsCaptor;

    @Captor
    private ArgumentCaptor<MsgUtils.GetMsgFunction> getMsgFunctionArgumentCaptor;

    @Test
    @DisplayName("получить авторов из сервиса и форматировать вывод")
    void shouldShowAuthors() throws Exception {

        when(authorService.getAll()).thenReturn(AUTHORS);
        when(msgUtils.listToLines(authorsCaptor.capture())).thenReturn(RESPONSE_TO_LINES);
        when(msgUtils.getMsgWithSimpleExceptionHandler(getMsgFunctionArgumentCaptor.capture()))
                .thenReturn(RESPONSE_WITH_EXCEPTION_HANDLE);

        var response = authorFlowCommands.showAuthors();
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(RESPONSE_TO_LINES, getMsgFunctionArgumentCaptor.getValue().apply());
        assertEquals(AUTHORS, authorsCaptor.getValue());
    }
}