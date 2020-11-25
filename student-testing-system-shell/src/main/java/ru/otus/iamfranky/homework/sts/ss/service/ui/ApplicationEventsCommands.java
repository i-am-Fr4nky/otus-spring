package ru.otus.iamfranky.homework.sts.ss.service.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamMsgPaths;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamService;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.ss.service.lang.LanguageService;
import ru.otus.iamfranky.homework.sts.ss.service.student.StudentAware;

import java.util.Locale;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands implements ExamMsgPaths, ApplicationMsgPaths {

    private final StudentAware studentAware;
    private final LanguageService languageService;
    private final ExamService examService;
    private final MessageService messageService;


    @ShellMethod(value = "Login or change user", key = {"login", "l"})
    public String login(@ShellOption String name, @ShellOption String surname) {
        studentAware.setStudent(new Student(name, surname));
        return messageService.getMsg(WELCOME_MSG, name, surname);
    }

    @ShellMethod(value = "Switch language.", key = {"language", "lang"})
    public String switchLanguage(@ShellOption(defaultValue = "en") String languageCode) {
        try {
            var locale = new Locale(languageCode);
            languageService.switchLanguage(locale);
            return messageService.getMsg(LOCALE_MSG, locale);
        } catch (Exception e) {
            return messageService.getMsg(ERROR_MSG, e.getLocalizedMessage());
        }
    }

    @ShellMethod(value = "Start exam.", key = {"exam"})
    @ShellMethodAvailability(value = "isUserLogin")
    public String startExam() {
        try {
            var result = examService.exam(studentAware.getStudent());
            return result ? messageService.getMsg(SUCCESS_MSG) : messageService.getMsg(FAILED_MSG);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return messageService.getMsg(ERROR_MSG, e.getMessage());
        }
    }

    private Availability isUserLogin() {
        return studentAware.isPresent() ? Availability.available() :
                Availability.unavailable(messageService.getMsg(NOT_LOGIN_MSG));
    }
}
