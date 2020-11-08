package ru.otus.iamfranky.homework.service.answers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.dao.QuestionDao;
import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.exception.QuestionsReadingException;
import ru.otus.iamfranky.homework.service.ui.UIService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {

    private static final String QUESTION_MSG = "%d. %s";

    private final QuestionDao questionDao;
    private final UIService uiService;

    @Override
    public List<Answer> getAnswers(Student student) throws QuestionsReadingException {
        var questions = questionDao.findQuestion();
        return IntStream.range(0, questions.size())
                .mapToObj(i -> {
                    var question = questions.get(i);
                    uiService.inform(String.format(QUESTION_MSG, i + 1, question.getText()));
                    var userAnswer = uiService.read();
                    return new Answer(question.getText(), question.getCorrectAnswer(), userAnswer);
                }
        ).collect(Collectors.toList());
    }
}