package ru.otus.iamfranky.homework.books.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.iamfranky.homework.books.service.AuthorService;
import ru.otus.iamfranky.homework.books.utils.MsgUtils;
import ru.otus.iamfranky.homework.books.utils.StringUtils;

@RequiredArgsConstructor
@ShellComponent
public class AuthorFlowCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Show all authors", key = {"authors", "a"})
    public String showAuthors() {
        return MsgUtils.getMsgWithSimpleExceptionHandler(() -> {
            var authors = authorService.getAll();
            return StringUtils.listToLines(authors);
        });
    }
}
