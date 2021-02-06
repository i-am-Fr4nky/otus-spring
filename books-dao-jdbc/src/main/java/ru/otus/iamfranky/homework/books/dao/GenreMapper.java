package ru.otus.iamfranky.homework.books.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.iamfranky.homework.books.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    private final static String ID = "g_id";
    private final static String NAME = "g_name";

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Genre (
                resultSet.getInt(ID),
                resultSet.getString(NAME)
        );
    }
}
