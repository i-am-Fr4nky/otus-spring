package ru.otus.iamfranky.homework.sts.sb.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Answer extends Question {

    private final String answer;

    public Answer(String text, String correctAnswer, String answer) {
        super(text, correctAnswer);
        this.answer = answer;
    }
}
