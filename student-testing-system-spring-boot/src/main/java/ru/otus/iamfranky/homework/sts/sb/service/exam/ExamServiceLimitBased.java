package ru.otus.iamfranky.homework.sts.sb.service.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.sts.sb.domain.Answer;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.exception.QuestionsReadingException;
import ru.otus.iamfranky.homework.sts.sb.properties.AnswerProperties;
import ru.otus.iamfranky.homework.sts.sb.service.answers.AnswersService;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AnswerProperties.class)
public class ExamServiceLimitBased implements ExamService {

    private static final String START_MSG = "sts.sb.msg.exam.start";

    private final AnswerProperties answerProperties;
    private final AnswersService answersService;
    private final UIService uiService;
    private final MessageService messageService;

    public boolean isSuccess(List<Answer> answers) {
        var correct = answers.stream()
                .filter(a -> a.getAnswer().equalsIgnoreCase(a.getCorrectAnswer()))
                .collect(Collectors.toList());
        return correct.size() >= answerProperties.getLimit();
    }

    @Override
    public boolean exam(Student student) throws QuestionsReadingException {
        uiService.inform(messageService.getMsg(START_MSG, new String[]{student.getName(), student.getSurname()}));
        var answers = answersService.getAnswers(student);
        return isSuccess(answers);
    }
}