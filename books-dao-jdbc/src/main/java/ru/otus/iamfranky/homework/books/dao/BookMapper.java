package ru.otus.iamfranky.homework.books.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.iamfranky.homework.books.domain.Author;
import ru.otus.iamfranky.homework.books.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    private final static RowMapper<Author> AUTHOR_MAPPER = new AuthorMapper();

    private final static String ID = "b_id";
    private final static String NAME = "b_name";
    private final static String DESC = "b_description";

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Book (
                resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getString(DESC),
                AUTHOR_MAPPER.mapRow(resultSet, i)
        );
    }
}