package ru.otus.iamfranky.homework.books.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.iamfranky.homework.books.service.GenreService;
import ru.otus.iamfranky.homework.books.utils.MsgUtils;

@RequiredArgsConstructor
@ShellComponent
public class GenreFlowCommands {

    private final GenreService genreService;
    private final MsgUtils msgUtils;

    @ShellMethod(value = "Show all genres", key = {"genres", "g"})
    public String showGenres() {
        return msgUtils.getMsgWithSimpleExceptionHandler(() -> {
            var genres = genreService.getAll();
            return msgUtils.listToLines(genres);
        });
    }
}