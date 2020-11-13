package ru.otus.iamfranky.homework.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.otus.iamfranky.homework.Main;
import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.service.answers.AnswersService;
import ru.otus.iamfranky.homework.service.exam.ExamService;
import ru.otus.iamfranky.homework.service.exam.ExamServiceLimitBased;
import ru.otus.iamfranky.homework.service.ui.UIService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Main.class)
public class ExamServiceLimitBasedTest {

    private final AnswersService answersService = mock(AnswersService.class);
    private final UIService uiService = mock(UIService.class);

    private ExamService examService;

    @Value("${student.testing.system.answer.limit}")
    private int answerLimit;

    @Value("${student.testing.system.answer.all}")
    private int allAnswer;

    private Student student = new Student("name", "surname");

    @Before
    public void before() throws IOException {
        this.examService = new ExamServiceLimitBased(answerLimit, answersService, uiService);
        if (allAnswer < answerLimit)
            throw new IOException("Answer limit is biggest that all answers size. Please, check test property.");
    }

    @Test
    public void shouldSuccessExam() throws Exception {

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