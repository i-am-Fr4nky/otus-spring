package ru.otus.iamfranky.homework.service;

import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;

import java.util.List;

public interface AnswersService {

    List<Answer> getAnswers(Student student) throws Exception;
}