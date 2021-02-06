package ru.otus.iamfranky.homework.books.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Методы утилит для обработки сообщений должны ")
class MsgUtilsTest {

    private static final String EXCEPTION_MSG = "Exception message";
    private static final String STRING_ELEMENT_1 = "element1";
    private static final String STRING_ELEMENT_2 = "element2";
    private static final String STRING_RESPONSE = "response";

    private final MsgUtils msgUtils = new MsgUtils();

    @Test
    @DisplayName("Передавать сообщения без изменений")
    void shouldGetMsg() {
        var result = msgUtils.getMsgWithSimpleExceptionHandler(() -> STRING_RESPONSE);
        assertEquals(STRING_RESPONSE, result);
    }

    @Test
    @DisplayName("Передавать сообщения исключений")
    void shouldGetExceptionMsg() {
        var result = msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            throw new Exception(EXCEPTION_MSG);
        });
        assertEquals(EXCEPTION_MSG, result);
    }

    @Test
    @DisplayName("Форматировать вывод списков")
    void shouldConvertListToLines() {
        var list = List.of(STRING_ELEMENT_1, STRING_ELEMENT_2);
        var result = msgUtils.listToLines(list);
        assertEquals(STRING_ELEMENT_1 + System.lineSeparator() +
                STRING_ELEMENT_2 + System.lineSeparator(), result);
    }
}