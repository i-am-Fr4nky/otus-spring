package ru.otus.iamfranky.homework.sts.sb.service.exam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.sts.sb.domain.Answer;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.exception.QuestionsReadingException;
import ru.otus.iamfranky.homework.sts.sb.service.answers.AnswersService;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceLimitBased implements ExamService {

    private static final String START_MSG = "sts.sb.msg.exam.start";

    private final int answerLimit;
    private final AnswersService answersService;
    private final UIService uiService;
    private final MessageService messageService;

    public ExamServiceLimitBased(
            @Value("${student.testing.system.answer.limit:2}") int answerLimit,
            AnswersService answersService,
            UIService uiService, MessageService messageService) {

        this.answerLimit = answerLimit;
        this.answersService = answersService;
        this.uiService = uiService;
        this.messageService = messageService;
    }

    public boolean isSuccess(List<Answer> answers) {
        var correct = answers.stream()
                .filter(a -> a.getAnswer().equalsIgnoreCase(a.getCorrectAnswer()))
                .collect(Collectors.toList());
        return correct.size() >= answerLimit;
    }

    @Override
    public boolean exam(Student student) throws QuestionsReadingException {
        uiService.inform(messageService.getMsg(START_MSG, new String[]{student.getName(), student.getSurname()}));
        var answers = answersService.getAnswers(student);
        return isSuccess(answers);
    }
}