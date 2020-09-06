package ru.otus.iamfranky.homework.service;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.dao.QuestionDao;
import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {

    private static final String START_MSG = "%s %s, hello. Exam is starting, please answer the questions:";
    private static final String QUESTION_MSG = "%d. %s";

    private final QuestionDao questionDao;

    @Override
    public List<Answer> getAnswers(Student student) throws Exception {
        System.out.println(String.format(START_MSG, student.getName(), student.getSurname()));
        var in = new Scanner(System.in);
        var questions = questionDao.findQuestion();

        return IntStream.range(0, questions.size())
                .mapToObj(i -> {
                    var question = questions.get(i);
                    System.out.println(String.format(QUESTION_MSG, i + 1, question.getText()));
                    String userAnswer = in.nextLine();
                    return new Answer(question.getText(), question.getCorrectAnswer(), userAnswer);
                }
        ).collect(Collectors.toList());
    }
}