package ru.otus.iamfranky.homework.sts.sb.dao;

import ru.otus.iamfranky.homework.sts.sb.domain.Question;
import ru.otus.iamfranky.homework.sts.sb.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {

    List<Question> findQuestion() throws QuestionsReadingException;
}