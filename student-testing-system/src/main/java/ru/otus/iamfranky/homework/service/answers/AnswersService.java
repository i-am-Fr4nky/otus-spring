package ru.otus.iamfranky.homework.service.answers;

import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.exception.QuestionsReadingException;

import java.util.List;

public interface AnswersService {

    List<Answer> getAnswers(Student student) throws QuestionsReadingException;
}