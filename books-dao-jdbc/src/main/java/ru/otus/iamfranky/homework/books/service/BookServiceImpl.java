package ru.otus.iamfranky.homework.books.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.books.dao.BooksDao;
import ru.otus.iamfranky.homework.books.domain.Author;
import ru.otus.iamfranky.homework.books.domain.Book;
import ru.otus.iamfranky.homework.books.domain.Genre;
import ru.otus.iamfranky.homework.books.exception.BookNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BooksDao booksDao;

    @Override
    public List<Book> getAll() throws BookNotFoundException {
        try {
            var books = booksDao.getAll();
            if (books.size() == 0) {
                throw new BookNotFoundException();
            }
            return books;
        } catch (Exception e) {
            log.error("Get all error", e);
            throw e;
        }
    }

    @Override
    public List<Book> searchByName(String name) throws BookNotFoundException {
        try {
            return booksDao.searchByName(name);
        } catch (Exception e) {
            log.error("Search by name error", e);
            throw e;
        }
    }

    @Override
    public Book get(int id) throws BookNotFoundException {
        try {
            var book = booksDao.get(id);
            if (book != null) {
                throw new BookNotFoundException(id); // TODO test exception
            }
            return book;
        } catch (Exception e) {
            log.error("Get by id error", e);
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try {
            booksDao.delete(id);
        } catch (Exception e) {
            log.error("Delete error", e);
            throw e;
        }
    }

    @Override
    public Book add(int authorId, String name, String desc, int[] genreIds) {
        try {
            var book = toBook(authorId, name, desc, genreIds);
            return booksDao.insert(book);
        } catch (Exception e) {
            log.error("Add error", e);
            throw e;
        }
    }

    @Override
    public void update(int id, int authorId, String name, String desc, int[] genreIds) {
        try {
            var book = toBook(authorId, name, desc, genreIds);
            book.setId(id);
            booksDao.update(book);
        } catch (Exception e) {
            log.error("Update error", e);
            throw e;
        }
    }

    private Book toBook(int authorId, String name, String desc, int[] genreIds) {
        var author = Author.forParams(authorId);
        var genres = Arrays.stream(genreIds)
                .mapToObj(id -> Genre.forParams(id))
                .collect(Collectors.toSet());
        var book = Book.forParams(name, desc, author, genres);
        return book;
    }
}
