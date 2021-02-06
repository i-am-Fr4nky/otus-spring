package ru.otus.iamfranky.homework.books.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.iamfranky.homework.books.dao.GenreDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static ru.otus.iamfranky.homework.books.fixtures.GenreFixtures.GENRES_LIST;

@DisplayName("Методы сервиса работы с жанрами должны ")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private GenreServiceImpl genreService;

    @Test
    @DisplayName("вызывать методы DAO и возвращать результат")
    public void shouldGetAll() {
        when(genreDao.getAll()).thenReturn(GENRES_LIST);
        var result = genreService.getAll();
        assertEquals(GENRES_LIST, result);
    }

    @Test
    @DisplayName("пробрасывать любое исключение без изменений")
    void shouldThrowsAllExceptions() {
        var expectedException = new RuntimeException("Exception");
        when(genreDao.getAll()).thenThrow(expectedException);
        var actualException = assertThrows(RuntimeException.class, () -> genreService.getAll());
        assertEquals(expectedException, actualException);
    }
}