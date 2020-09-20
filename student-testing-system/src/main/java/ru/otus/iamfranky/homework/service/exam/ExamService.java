package ru.otus.iamfranky.homework.service.exam;

import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.exception.QuestionsReadingException;

public interface ExamService {

    boolean exam(Student student) throws QuestionsReadingException;
}