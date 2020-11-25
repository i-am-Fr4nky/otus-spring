package ru.otus.iamfranky.homework.sts.sb.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "student.testing.system.answer.limit")
public class AnswerProperties {

    private int limit;
}
