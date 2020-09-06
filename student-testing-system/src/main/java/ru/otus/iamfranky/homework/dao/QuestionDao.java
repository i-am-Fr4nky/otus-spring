package ru.otus.iamfranky.homework.dao;

import ru.otus.iamfranky.homework.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findQuestion() throws Exception;
}