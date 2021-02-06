package ru.otus.iamfranky.homework.sts.ss.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class Language {

    private Locale locale;
    private String file;
}
