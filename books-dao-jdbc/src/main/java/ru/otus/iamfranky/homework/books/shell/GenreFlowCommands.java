package ru.otus.iamfranky.homework.books.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.iamfranky.homework.books.service.GenreService;
import ru.otus.iamfranky.homework.books.utils.StringUtils;

@RequiredArgsConstructor
@ShellComponent
public class GenreFlowCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Show all genres", key = {"genres", "g"})
    public String showGenres() {
        var genres = genreService.getAll();
        return StringUtils.listToLines(genres);
    }

}