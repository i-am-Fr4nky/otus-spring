package ru.otus.iamfranky.homework.sts.ss.service.student;

import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.ss.exception.StudentNotSpecifiedException;

public interface StudentAware {

    void setStudent(Student student);
    Student getStudent() throws StudentNotSpecifiedException;
    boolean isPresent();
}
