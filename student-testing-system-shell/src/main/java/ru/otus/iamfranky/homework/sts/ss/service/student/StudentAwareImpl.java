package ru.otus.iamfranky.homework.sts.ss.service.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.ss.exception.StudentNotSpecifiedException;

import java.util.Optional;

@Slf4j
@Component
public class StudentAwareImpl implements StudentAware {

    private Optional<Student> student = Optional.empty();

    @Override
    public void setStudent(Student student) {
        this.student = Optional.of(student);
        log.debug("Current student set as: {}", student);
    }

    @Override
    public Student getStudent() throws StudentNotSpecifiedException {
        return student.orElseThrow(() -> new StudentNotSpecifiedException());
    }

    @Override
    public boolean isPresent() {
        return student.isPresent();
    }

}