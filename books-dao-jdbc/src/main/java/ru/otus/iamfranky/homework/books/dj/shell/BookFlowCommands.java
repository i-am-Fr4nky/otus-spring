package ru.otus.iamfranky.homework.books.dj.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookFlowCommands {

    @ShellMethod(value = "Login or change user", key = {"books", "b"})
    public String showBooks() {
        return "";
    }

}
