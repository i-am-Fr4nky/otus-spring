package ru.otus.iamfranky.homework.sts.ss.service.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.iamfranky.homework.sts.sb.config.LocalizationProperties;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.ss.service.student.StudentAware;

import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final StudentAware studentAware;
    private final LocalizationProperties localizationProperties;

    @ShellMethod(value = "Login or change user", key = {"login", "l"})
    public String login(@ShellOption String name, @ShellOption String surname) {
        studentAware.setStudent(new Student(name, surname));
        return String.format("Добро пожаловать: %s, %s", name, surname);
    }

    @ShellMethod(value = "Change language.", key = {"language", "lang"})
    public String changeLanguage(@ShellOption(defaultValue = "en") String locale) { // TODO use enum
        var loc = new Locale(locale);
        localizationProperties.setLocale(loc);
        return String.format("Locale changed: %s", loc);
    }

    @ShellMethod(value = "Start exam.", key = {"exam"})
    public String startExam() {
        return String.format("Exam is started.");
    }

}
