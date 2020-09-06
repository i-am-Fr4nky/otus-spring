package ru.otus.iamfranky.homework.domain;

import lombok.Getter;

@Getter
public class Answer extends Question {

    private final String answer;

    public Answer(String text, String correctAnswer, String answer) {
        super(text, correctAnswer);
        this.answer = answer;
    }
}
