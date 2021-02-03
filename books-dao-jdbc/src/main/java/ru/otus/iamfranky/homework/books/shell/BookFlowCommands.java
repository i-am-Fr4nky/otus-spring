package ru.otus.iamfranky.homework.books.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.iamfranky.homework.books.service.BookService;
import ru.otus.iamfranky.homework.books.utils.MsgUtils;

@RequiredArgsConstructor
@ShellComponent
public class BookFlowCommands {

    private static final String INSERT_MSG = "Adding book success, id: %s";
    private static final String UPDATE_MSG = "Update book success";
    private static final String DELETE_MSG = "Delete success";

    private final BookService bookService;
    private final MsgUtils msgUtils;

    @ShellMethod(value = "Show all books", key = {"books", "bs"})
    public String showBooks() {
        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            var books = bookService.getAll();
            return msgUtils.listToLines(books);
        });
    }

    @ShellMethod(value = "Get book", key = {"book", "b"})
    public String getBook(@ShellOption int id) {
        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            var book = bookService.get(id);
            return book.toString();
        });
    }

    @ShellMethod(value = "Find book", key = {"find", "f"})
    public String findBooks(@ShellOption String name) {
        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            var books = bookService.searchByName(name);
            return msgUtils.listToLines(books);
        });
    }

    @ShellMethod(value = "Delete book", key = {"delete", "del"})
    public String delete(@ShellOption int bookId) {
        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            bookService.delete(bookId);
            return DELETE_MSG;
        });
    }

    @ShellMethod(value = "Add new book", key = {"add"})
    public String add(@ShellOption int authorId, @ShellOption String name,
                      @ShellOption String desc, @ShellOption int[] genreIds) {

        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            var book = bookService.add(authorId, name, desc, genreIds);
            return String.format(INSERT_MSG, book.getId());
        });
    }

    @ShellMethod(value = "Update book", key = {"update", "upd"})
    public String update(@ShellOption int id, @ShellOption int authorId, @ShellOption String name,
                         @ShellOption String desc, @ShellOption int[] genreIds) {

        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            bookService.update(id, authorId, name, desc, genreIds);
            return String.format(UPDATE_MSG);
        });
    }
}
