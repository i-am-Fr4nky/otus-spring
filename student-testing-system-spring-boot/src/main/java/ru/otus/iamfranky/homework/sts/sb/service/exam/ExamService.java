package ru.otus.iamfranky.homework.sts.sb.service.exam;

import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.exception.QuestionsReadingException;

public interface ExamService {

    boolean exam(Student student) throws QuestionsReadingException;
}