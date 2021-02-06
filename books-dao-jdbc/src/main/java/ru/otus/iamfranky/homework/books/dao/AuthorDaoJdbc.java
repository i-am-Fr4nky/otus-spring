package ru.otus.iamfranky.homework.books.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.iamfranky.homework.books.domain.Author;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private static final RowMapper<Author> AUTHOR_MAPPER = new AuthorMapper();

    private final NamedParameterJdbcOperations jdbc;

    private static final String GET_ALL_QUERY =
            "SELECT " +
            "  a.id AS a_id, " +
            "  a.name AS a_name, " +
            "  a.surname AS a_surname, " +
            "  a.middle_name AS a_middle_name " +
            "FROM " +
            "  author AS a";

    @Override
    public List<Author> getAll() {
        return jdbc.query(GET_ALL_QUERY, AUTHOR_MAPPER);
    }
}
