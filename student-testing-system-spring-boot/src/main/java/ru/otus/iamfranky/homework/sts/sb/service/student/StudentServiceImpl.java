package ru.otus.iamfranky.homework.sts.sb.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final String NAME_MSG = "sts.sb.msg.student.name";
    private static final String SURNAME_MSG = "sts.sb.msg.student.surname";

    private final UIService uiService;
    private final MessageService messageService;

    @Override
    public Student getStudent() {

        uiService.inform(messageService.getMsg(NAME_MSG));
        var name = uiService.read();

        uiService.inform(messageService.getMsg(SURNAME_MSG));
        var surname = uiService.read();

        return new Student(name, surname);
    }
}