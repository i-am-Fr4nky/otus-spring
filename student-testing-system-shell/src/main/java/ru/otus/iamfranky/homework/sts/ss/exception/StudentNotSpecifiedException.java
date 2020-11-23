package ru.otus.iamfranky.homework.sts.ss.exception;

public class StudentNotSpecifiedException extends Exception {

    private final static String MSG = "Student not specified";

    public StudentNotSpecifiedException() {
        super(MSG);
    }
}
