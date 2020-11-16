package ru.otus.iamfranky.homework.sts.sb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.iamfranky.homework.sts.sb.domain.Answer;
import ru.otus.iamfranky.homework.sts.sb.domain.Student;
import ru.otus.iamfranky.homework.sts.sb.service.answers.AnswersService;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamService;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamServiceLimitBased;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExamServiceLimitBasedTest {

    @Mock
    private AnswersService answersService;

    @Mock
    private UIService uiService;

    @Mock
    private MessageService messageService;

    private ExamService examService;

    @Value("${student.testing.system.answer.limit}")
    private int answerLimit;

    @Value("${student.testing.system.answer.all}")
    private int allAnswer;

    private Student student = new Student("name", "surname");

    @BeforeEach
    void before() throws RuntimeException {
        this.examService = new ExamServiceLimitBased(answerLimit, answersService, uiService, messageService);
        if (allAnswer == 0 && answerLimit == 0)
            throw new RuntimeException("Values of answer limit and answers size are 0. Please, check test property.");
        if (allAnswer < answerLimit)
            throw new RuntimeException("Answer limit is biggest that all answers size. Please, check test property.");
    }

    @Test
    void shouldSuccessExam() throws Exception {

        // Given
        var answers = new ArrayList<Answer>() {{
            addAll(getAnswers(answerLimit, true));
            addAll(getAnswers(allAnswer - answerLimit, false));
        }};

        // When
        when(answersService.getAnswers(student)).thenReturn(answers);
        doNothing().when(uiService).inform(any());
        var result = examService.exam(student);

        // Then
        assertTrue(result);
    }

    @Test
    public void shouldFailedExam() throws Exception {

        // Given
        var correctAnswer = answerLimit - 1;
        var answers = new ArrayList<Answer>() {{
            addAll(getAnswers(correctAnswer, true));
            addAll(getAnswers(allAnswer - correctAnswer, false));
        }};

        // When
        when(answersService.getAnswers(student)).thenReturn(answers);
        doNothing().when(uiService).inform(any());
        var result = examService.exam(student);

        // Then
        assertFalse(result);
    }

    private List<Answer> getAnswers(int size, boolean correct) {
        var answers = new ArrayList<Answer>();
        for (int i = 0; i < size; i++) {
            var textAnswer = getRandomString();
            answers.add(new Answer(getRandomString(), textAnswer,
                    correct ? textAnswer : getRandomString()));
        }
        return answers;
    }

    private String getRandomString() {
        return UUID.randomUUID().toString();
    }
}