package ru.otus.iamfranky.homework.sts.sb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Question {

    private String text;
    private String correctAnswer;
}
