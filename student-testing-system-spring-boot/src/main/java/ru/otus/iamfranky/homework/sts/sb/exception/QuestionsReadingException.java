package ru.otus.iamfranky.homework.sts.sb.exception;

public class QuestionsReadingException extends Exception {

    private final static String MSG = "File reading error";

    public QuestionsReadingException(Exception e) {
        super(MSG, e);
    }

}
