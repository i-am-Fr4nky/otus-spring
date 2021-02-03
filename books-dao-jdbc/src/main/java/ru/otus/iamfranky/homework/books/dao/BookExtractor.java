package ru.otus.iamfranky.homework.books.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import ru.otus.iamfranky.homework.books.domain.Book;
import ru.otus.iamfranky.homework.books.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookExtractor implements ResultSetExtractor<List<Book>> {

    private final static RowMapper<Genre> GENRE_MAPPER = new GenreMapper();
    private final static RowMapper<Book> BOOK_MAPPER = new BookMapper();

    @Override
    public List<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        var books = new ArrayList<Book>();
        for (int i = 0; resultSet.next(); i++) {
            var book = BOOK_MAPPER.mapRow(resultSet, i);
            var genre = GENRE_MAPPER.mapRow(resultSet, i);
            if (!books.contains(book)) {
                var genres = new HashSet<Genre>();
                book.setGenres(genres);
                books.add(book);
            }
            books.get(books.size() - 1).getGenres().add(genre);
        }
        return books;
    }
}