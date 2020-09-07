package ru.otus.iamfranky.homework.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ru.otus.iamfranky.homework.domain.Answer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExamServiceLimitBasedTest {

    private final AnswersService answersService = mock(AnswersService.class);

    private ExamService examService;

    private int answerLimit = 2;

    @Before
    public void before() {
        this.examService = new ExamServiceLimitBased(answerLimit, answersService);
    }

    @Test
    public void shouldSuccessExam() throws Exception {

        // Given
        var answers = List.of(
                new Answer("text", "answer", "answer"),
                new Answer("text", "answer", "answer"),
                new Answer("text", "correctAnswer", "answer")
        );

        // When
        when(answersService.getAnswers(any())).thenReturn(answers);
        var result = examService.exam(any());

        // Then
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void shouldFailedExam() throws Exception {

        // Given
        var answers = List.of(
                new Answer("text", "answer", "answer"),
                new Answer("text", "correctAnswer", "answer"),
                new Answer("text", "correctAnswer", "answer")
        );

        // When
        when(answersService.getAnswers(any())).thenReturn(answers);
        var result = examService.exam(any());

        // Then
        assertEquals(Boolean.FALSE, result);
    }

}