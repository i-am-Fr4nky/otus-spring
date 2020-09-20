package ru.otus.iamfranky.homework.service.exam;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.exception.QuestionsReadingException;
import ru.otus.iamfranky.homework.service.answers.AnswersService;
import ru.otus.iamfranky.homework.service.ui.UIService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExamServiceLimitBased implements ExamService {

    private static final String START_MSG = "%s %s, hello. Exam is starting, please answer the questions:";


    private final int answerLimit;
    private final AnswersService answersService;
    private final UIService uiService;

    public boolean isSuccess(List<Answer> answers) {
        var correct = answers.stream()
                .filter(a -> a.getAnswer().equalsIgnoreCase(a.getCorrectAnswer()))
                .collect(Collectors.toList());
        return correct.size() >= answerLimit;
    }

    @Override
    public boolean exam(Student student) throws QuestionsReadingException {
        uiService.inform(String.format(START_MSG, student.getName(), student.getSurname()));
        var answers = answersService.getAnswers(student);
        return isSuccess(answers);
    }
}