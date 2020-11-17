package ru.otus.iamfranky.homework.sts.sb.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Getter
@Setter
@ConfigurationProperties(prefix = "student.testing.system.localization")
public class LocalizationProperties {

    private Locale locale;
}
