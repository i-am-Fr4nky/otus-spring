package ru.otus.iamfranky.homework.books.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.iamfranky.homework.books.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    private final static String ID = "a_id";
    private final static String NAME = "a_name";
    private final static String SURNAME = "a_surname";
    private final static String MIDDLE_NAME = "a_middle_name";

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Author (
                resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getString(SURNAME),
                resultSet.getString(MIDDLE_NAME)
        );
    }
}
