package ru.otus.iamfranky.homework.service;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExamServiceLimitBased implements ExamService {

    private final int answerLimit;
    private final AnswersService answersService;

    public boolean isSuccess(List<Answer> answers) {
        var correct = answers.stream()
                .filter(a -> a.getAnswer().equalsIgnoreCase(a.getCorrectAnswer()))
                .collect(Collectors.toList());
        return correct.size() >= answerLimit;
    }

    @Override
    public boolean exam(Student student) throws Exception {
        var answers = answersService.getAnswers(student);
        return isSuccess(answers);
    }
}