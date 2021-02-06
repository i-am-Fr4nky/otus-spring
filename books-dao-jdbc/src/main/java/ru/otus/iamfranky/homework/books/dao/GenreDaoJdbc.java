package ru.otus.iamfranky.homework.books.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.iamfranky.homework.books.domain.Genre;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private static final RowMapper<Genre> GENRE_MAPPER = new GenreMapper();

    private final NamedParameterJdbcOperations jdbc;

    private static final String GET_ALL_QUERY =
            "SELECT " +
            "  g.id AS g_id, " +
            "  g.name AS g_name " +
            "FROM " +
            "  genre AS g";

    @Override
    public List<Genre> getAll() {
        return jdbc.query(GET_ALL_QUERY, GENRE_MAPPER);
    }
}
