package ru.otus.iamfranky.homework.books.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.iamfranky.homework.books.service.AuthorService;
import ru.otus.iamfranky.homework.books.utils.MsgUtils;

@RequiredArgsConstructor
@ShellComponent
public class AuthorFlowCommands {

    private final AuthorService authorService;
    private final MsgUtils msgUtils;

    @ShellMethod(value = "Show all authors", key = {"authors", "a"})
    public String showAuthors() {
        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            var authors = authorService.getAll();
            return msgUtils.listToLines(authors);
        });
    }
}
