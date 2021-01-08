package ru.otus.iamfranky.homework.books.utils;

import java.util.List;

public class StringUtils {

    public static String listToLines(List list) {
        var builder = new StringBuilder();
        for (var item : list) {
            builder.append(item.toString());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
