package ru.otus.iamfranky.homework.sts.ss.exception;

import java.util.Locale;

public class LanguageNotSupportedException extends Exception {

    private final static String MSG = "Language '%s' not supported";

    public LanguageNotSupportedException(Locale locale) {
        super(String.format(MSG, locale));
    }
}
