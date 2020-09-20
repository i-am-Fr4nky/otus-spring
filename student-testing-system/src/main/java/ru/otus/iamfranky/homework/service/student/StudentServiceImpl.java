package ru.otus.iamfranky.homework.service.student;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.service.ui.UIService;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final String NAME_MSG = "Please enter your name amd type Enter:";
    private static final String SURNAME_MSG = "Please enter your surname amd type Enter:";

    private final UIService uiService;

    @Override
    public Student getStudent() {

        uiService.inform(NAME_MSG);
        var name = uiService.read();

        uiService.inform(SURNAME_MSG);
        var surname = uiService.read();

        return new Student(name, surname);
    }
}