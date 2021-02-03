package ru.otus.iamfranky.homework.books.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.iamfranky.homework.books.domain.Genre;
import ru.otus.iamfranky.homework.books.service.GenreService;
import ru.otus.iamfranky.homework.books.utils.MsgUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.iamfranky.homework.books.fixtures.GenreFixtures.GENRES_LIST;
import static ru.otus.iamfranky.homework.books.fixtures.MsgUtilsFixtures.RESPONSE_TO_LINES;
import static ru.otus.iamfranky.homework.books.fixtures.MsgUtilsFixtures.RESPONSE_WITH_EXCEPTION_HANDLE;

@DisplayName("Методы компонента работы с жанрами должны")
@SpringBootTest(classes = GenreFlowCommands.class)
class GenreFlowCommandsTest {

    @MockBean
    private MsgUtils msgUtils;

    @MockBean
    private GenreService genreService;

    @Autowired
    private GenreFlowCommands genreFlowCommands;

    @Captor
    private ArgumentCaptor<List<Genre>> genresCaptor;

    @Captor
    private ArgumentCaptor<MsgUtils.GetMsgFunction> getMsgFunctionArgumentCaptor;


    @Test
    @DisplayName("получить жанры из сервиса и форматировать вывод")
    void shouldShowGenres() throws Exception {
        when(genreService.getAll()).thenReturn(GENRES_LIST);
        when(msgUtils.listToLines(genresCaptor.capture())).thenReturn(RESPONSE_TO_LINES);
        when(msgUtils.getMsgWithSimpleExceptionHandler(getMsgFunctionArgumentCaptor.capture()))
                .thenReturn(RESPONSE_WITH_EXCEPTION_HANDLE);

        var response = genreFlowCommands.showGenres();
        assertEquals(response, RESPONSE_WITH_EXCEPTION_HANDLE);
        assertEquals(RESPONSE_TO_LINES, getMsgFunctionArgumentCaptor.getValue().apply());
        assertEquals(GENRES_LIST, genresCaptor.getValue());
    }
}