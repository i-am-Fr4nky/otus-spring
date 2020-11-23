package ru.otus.iamfranky.homework.sts.ss.service.lang;

import ru.otus.iamfranky.homework.sts.ss.exception.LanguageNotSupportedException;

import java.util.Locale;

public interface LanguageService {

    void switchLanguage(Locale locale) throws LanguageNotSupportedException;
}
