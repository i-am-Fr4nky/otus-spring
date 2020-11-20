package ru.otus.iamfranky.homework.sts.ss.service.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.exception.QuestionsReadingException;
import ru.otus.iamfranky.homework.sts.sb.properties.LocalizationProperties;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamService;
import ru.otus.iamfranky.homework.sts.ss.exception.StudentNotSpecifiedException;
import ru.otus.iamfranky.homework.sts.ss.service.student.StudentAware;

import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
@EnableConfigurationProperties(LocalizationProperties.class)
public class ApplicationEventsCommands {

    private final StudentAware studentAware;
    private final LocalizationProperties localizationProperties;
    private final ExamService examService;

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
        try {
            var result = examService.exam(studentAware.getStudent());
            return result ? "Поздравляем с умпешной сдачей экзамена" :
                    "Попробуйте в другой раз";
        } catch (QuestionsReadingException e) {
            e.printStackTrace();
            return "Ошибка при чтении файла:" + e.getMessage();
        } catch (StudentNotSpecifiedException e) {
            e.printStackTrace();
            return "Ошибка. Необходимо залогиниться.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка. " + e.getLocalizedMessage();
        }
    }

}
