package ru.otus.iamfranky.homework.books.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {

    private final int id;
    private final String name;
    private final String surname;
    private final String middleName;

    public static Author forParams(int id) {
        return new Author(id, null, null, null);
    }
}