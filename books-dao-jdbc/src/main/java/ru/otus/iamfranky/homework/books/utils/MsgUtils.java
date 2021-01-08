package ru.otus.iamfranky.homework.books.utils;

public class MsgUtils {

    public static String getMsgWithSimpleExceptionHandler(GetMsgFunction getMsgFunction) {
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
}
