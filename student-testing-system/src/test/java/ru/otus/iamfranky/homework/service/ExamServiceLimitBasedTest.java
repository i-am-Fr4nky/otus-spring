package ru.otus.iamfranky.homework.service;

import org.junit.Before;
import org.junit.Test;
import ru.otus.iamfranky.homework.domain.Answer;
import ru.otus.iamfranky.homework.domain.Student;
import ru.otus.iamfranky.homework.service.answers.AnswersService;
import ru.otus.iamfranky.homework.service.exam.ExamService;
import ru.otus.iamfranky.homework.service.exam.ExamServiceLimitBased;
import ru.otus.iamfranky.homework.service.ui.UIService;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ExamServiceLimitBasedTest {

    private final AnswersService answersService = mock(AnswersService.class);
    private final UIService uiService = mock(UIService.class);

    private ExamService examService;

    private int answerLimit = 2;

    @Before
    public void before() {
        this.examService = new ExamServiceLimitBased(answerLimit, answersService, uiService);
    }

    @Test
    public void shouldSuccessExam() throws Exception {

        // Given
        var student = new Student("name", "surname");
        var answers = List.of(
                new Answer("text", "answer", "answer"),
                new Answer("text", "answer", "answer"),
                new Answer("text", "correctAnswer", "answer")
        );

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
        var student = new Student("name", "surname");
        var answers = List.of(
                new Answer("text", "answer", "answer"),
                new Answer("text", "correctAnswer", "answer"),
                new Answer("text", "correctAnswer", "answer")
        );

        // When
        when(answersService.getAnswers(student)).thenReturn(answers);
        doNothing().when(uiService).inform(any());
        var result = examService.exam(student);

        // Then
        assertFalse(result);
    }

}