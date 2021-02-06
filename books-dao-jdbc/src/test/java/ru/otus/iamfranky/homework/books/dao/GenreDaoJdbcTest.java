package ru.otus.iamfranky.homework.books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.iamfranky.homework.books.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    private List<Genre> allGenres = List.of(
            new Genre(1, "Genre 1"),
            new Genre(2, "Genre 2"));

    @Test
    @DisplayName("возвращать все жарны")
    void shouldGetAll() {
        var result = genreDaoJdbc.getAll();
        assertThat(result)
                .hasSize(allGenres.size())
                .containsAll(allGenres);
    }
}