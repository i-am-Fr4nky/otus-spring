package ru.otus.iamfranky.homework.sts.sb.service.answers;

import ru.otus.iamfranky.homework.sts.sb.domain.Answer;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.exception.QuestionsReadingException;

import java.util.List;

public interface AnswersService {

    List<Answer> getAnswers(Student student) throws QuestionsReadingException;
}