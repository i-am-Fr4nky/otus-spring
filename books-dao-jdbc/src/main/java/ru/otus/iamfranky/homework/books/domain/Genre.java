package ru.otus.iamfranky.homework.books.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Genre {

    private final int id;
    private final String name;

    public static Genre forParams(int id) {
        return new Genre(id, null);
    }
}
