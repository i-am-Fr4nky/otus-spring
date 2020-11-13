package ru.otus.iamfranky.homework.service.exam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.exception.QuestionsReadingException;
import ru.otus.iamfranky.homework.service.answers.AnswersService;
import ru.otus.iamfranky.homework.service.ui.UIService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceLimitBased implements ExamService {

    private static final String START_MSG = "%s %s, hello. Exam is starting, please answer the questions:";

    private final int answerLimit;
    private final AnswersService answersService;
    private final UIService uiService;

    public ExamServiceLimitBased(
            @Value("${student.testing.system.answer.limit:2}") int answerLimit,
            AnswersService answersService,
            UIService uiService) {

        this.answerLimit = answerLimit;
        this.answersService = answersService;
        this.uiService = uiService;
    }

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