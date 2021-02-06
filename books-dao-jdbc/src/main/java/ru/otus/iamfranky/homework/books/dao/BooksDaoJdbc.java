package ru.otus.iamfranky.homework.books.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.iamfranky.homework.books.domain.Book;
import ru.otus.iamfranky.homework.books.exception.BookNotFoundException;

import java.sql.Statement;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BooksDaoJdbc implements BooksDao {

    private final static ResultSetExtractor<List<Book>> BOOK_EXTRACTOR = new BookExtractor();

    private final NamedParameterJdbcOperations jdbc;

    private final static String INSERT_BOOKS_QUERY =
            "INSERT INTO book (author_id, name, description) VALUES (?, ?, ?)";
    @Override
    @Transactional
    public Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.getJdbcOperations().update(connection -> {
            var ps = connection.prepareStatement(INSERT_BOOKS_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, book.getAuthor().getId());
            ps.setString(2, book.getName());
            ps.setString(3, book.getDesc());
            return ps;
        }, keyHolder);
        book.setId(keyHolder.getKey().intValue());
        insertGenres(book);
        return book;
    }

    private final static String UPDATE_BOOKS_QUERY =
            "UPDATE book SET " +
            "  author_id = :authorId, " +
            "  name = :name, " +
            "  description = :description " +
            "WHERE id = :bookId";
    private final static String DELETE_GENRES =
            "DELETE FROM books_genre WHERE book_id = :bookId;";

    @Override
    @Transactional
    public void update(Book book) {
        var params = new MapSqlParameterSource()
                .addValue("authorId", book.getAuthor().getId())
                .addValue("name", book.getName())
                .addValue("description", book.getDesc())
                .addValue("bookId", book.getId());
        jdbc.update(UPDATE_BOOKS_QUERY, params);
        jdbc.update(DELETE_GENRES, params);
        insertGenres(book);
    }

    private final static String INSERT_BOOKS_GENRE_QUERY =
            "INSERT INTO books_genre (book_id, genre_id) VALUES (:booksId, :genreId);";
    private void insertGenres(Book book) {
        var batch = book.getGenres().stream()
                .map(g -> new MapSqlParameterSource()
                        .addValue("booksId", book.getId())
                        .addValue("genreId", g.getId()))
                .toArray(SqlParameterSource[]::new);

        jdbc.batchUpdate(INSERT_BOOKS_GENRE_QUERY, batch);
    }

    private final static String GET_QUERY =
            "SELECT " +
            "  b.id AS b_id, " +
            "  b.name AS b_name, " +
            "  b.description AS b_description, " +
            "  a.id AS a_id, " +
            "  a.name AS a_name, " +
            "  a.surname AS a_surname, " +
            "  a.middle_name AS a_middle_name, " +
            "  g.id AS g_id, " +
            "  g.name AS g_name " +
            "FROM " +
            "  book AS b" +
            "  INNER JOIN author AS a ON b.author_id = a.id " +
            "  INNER JOIN books_genre AS bg ON b.id = bg.book_id " +
            "  INNER JOIN genre AS g ON bg.genre_id = g.id " +
            "WHERE " +
            "  b.id = :id " +
            "ORDER BY b.id ";

    @Override
    public Book get(int id) throws BookNotFoundException {
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        var books = jdbc.query(GET_QUERY, params, BOOK_EXTRACTOR);
        if (books.isEmpty()) {
            throw new BookNotFoundException(id);
        } else if (books.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
        return books.get(0);
    }

    private final static String DELETE_QUERY =
            "DELETE FROM books_genre WHERE book_id = :id;" +
            "DELETE FROM book WHERE id = :id;";
    @Override
    public void delete(int id) {
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbc.update(DELETE_QUERY, params);
    }

    private final static String GET_ALL_QUERY =
            "SELECT " +
            "  b.id AS b_id, " +
            "  b.name AS b_name, " +
            "  b.description AS b_description, " +
            "  a.id AS a_id, " +
            "  a.name AS a_name, " +
            "  a.surname AS a_surname, " +
            "  a.middle_name AS a_middle_name, " +
            "  g.id AS g_id, " +
            "  g.name AS g_name " +
            "FROM " +
            "  book AS b " +
            "  INNER JOIN author AS a ON b.author_id = a.id " +
            "  INNER JOIN books_genre AS bg ON b.id = bg.book_id " +
            "  INNER JOIN genre AS g ON bg.genre_id = g.id " +
            "ORDER BY b.id";

    @Override
    public List<Book> getAll() throws BookNotFoundException {
        var books = jdbc.query(GET_ALL_QUERY, BOOK_EXTRACTOR);
        if (books.size() == 0) {
            throw new BookNotFoundException();
        }
        return books;
    }

    private final static String GET_BY_NAME_QUERY =
            "SELECT " +
            "  b.id AS b_id, " +
            "  b.name AS b_name, " +
            "  b.description AS b_description, " +
            "  a.id AS a_id, " +
            "  a.name AS a_name, " +
            "  a.surname AS a_surname, " +
            "  a.middle_name AS a_middle_name, " +
            "  g.id AS g_id, " +
            "  g.name AS g_name " +
            "FROM " +
            "  book AS b" +
            "  INNER JOIN author AS a on b.author_id = a.id " +
            "  INNER JOIN books_genre AS bg ON b.id = bg.book_id " +
            "  INNER JOIN genre AS g ON bg.genre_id = g.id " +
            "WHERE " +
            "  b.name LIKE :name " +
            "ORDER BY b.id";

    @Override
    public List<Book> searchByName(String name) throws BookNotFoundException {
        var params = new MapSqlParameterSource()
                .addValue("name", "%" + name + "%");
        var books = jdbc.query(GET_BY_NAME_QUERY, params, BOOK_EXTRACTOR);
        if (books.isEmpty()) {
            throw new BookNotFoundException(name);
        }
        return books;
    }
}
