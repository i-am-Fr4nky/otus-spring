package ru.otus.iamfranky.homework.books.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MsgUtils {

    public String getMsgWithSimpleExceptionHandler(GetMsgFunction getMsgFunction) {
        try {
            return getMsgFunction.apply();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @FunctionalInterface
    public interface GetMsgFunction {
        String apply() throws Exception;
    }

    public String listToLines(List list) {
        var builder = new StringBuilder();
        for (var item : list) {
            builder.append(item.toString());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
