package ru.otus.iamfranky.homework.dao;

import ru.otus.iamfranky.homework.domain.Question;
import ru.otus.iamfranky.homework.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {

    List<Question> findQuestion() throws QuestionsReadingException;
}