package ru.otus.iamfranky.homework.sts.ss.service.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.ss.exception.StudentNotSpecifiedException;

@Slf4j
@Component
public class StudentAwareImpl implements StudentAware {

    private Student student;

    @Override
    public void setStudent(Student student) {
        this.student = student;
        log.info("Current student set as: {}", student);
    }

    @Override
    public Student getStudent() throws StudentNotSpecifiedException {

        if (student == null)
            throw new StudentNotSpecifiedException();

        return student;
    }
}