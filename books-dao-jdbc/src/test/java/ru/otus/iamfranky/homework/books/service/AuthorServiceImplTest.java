package ru.otus.iamfranky.homework.books.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.iamfranky.homework.books.dao.AuthorDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static ru.otus.iamfranky.homework.books.fixtures.AuthorFixtures.AUTHORS;

@DisplayName("Методы сервиса работы с авторами должны ")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    private static final RuntimeException EXPECTED_EXCEPTION = new RuntimeException("Exception");

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private AuthorServiceImpl authorService;

    @Test
    @DisplayName("вызывать методы DAO и возвращать результат")
    public void shouldGetAll() {
        when(authorDao.getAll()).thenReturn(AUTHORS);
        var result = authorService.getAll();
        assertEquals(AUTHORS, result);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений")
    void shouldThrowsAllExceptions() {
        when(authorDao.getAll()).thenThrow(EXPECTED_EXCEPTION);
        var actualException = assertThrows(RuntimeException.class, () -> authorService.getAll());
        assertEquals(EXPECTED_EXCEPTION, actualException);
    }
}