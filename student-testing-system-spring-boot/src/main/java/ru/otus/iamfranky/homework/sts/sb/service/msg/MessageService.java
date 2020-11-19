package ru.otus.iamfranky.homework.sts.sb.service.msg;

public interface MessageService {

    String getMsg(String propertyPath);
    String getMsg(String propertyPath, Object... param);
}
