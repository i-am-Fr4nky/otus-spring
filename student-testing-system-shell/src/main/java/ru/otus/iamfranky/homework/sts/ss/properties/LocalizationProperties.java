package ru.otus.iamfranky.homework.sts.ss.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.otus.iamfranky.homework.sts.ss.domain.Language;

import java.util.List;
import java.util.Locale;

@Setter
@Getter
@ConfigurationProperties(prefix = "student.testing.system.localization")
public class LocalizationProperties extends ru.otus.iamfranky.homework.sts.sb.properties.LocalizationProperties {

    private Locale def;
    private List<Language> languages;

    @Override
    public Locale getLocale() {
        return def;
    }

    @Override
    public void setLocale(Locale locale) {
        this.def = locale;
    }
}
