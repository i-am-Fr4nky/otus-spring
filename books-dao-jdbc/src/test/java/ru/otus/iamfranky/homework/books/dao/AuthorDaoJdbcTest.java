package ru.otus.iamfranky.homework.books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.iamfranky.homework.books.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

    private List<Author> allAuthor = List.of(
            new Author(1, "Author 1 name", "Author 1 surname", "Author 1 middle name"),
            new Author(2, "Author 2 name", "Author 2 surname", "Author 2 middle name"));

    @Test
    @DisplayName("возвращать всех авторов")
    void shouldGetAll() {
        var result = authorDao.getAll();
        assertThat(result)
                .hasSize(allAuthor.size())
                .containsAll(allAuthor);
    }
}