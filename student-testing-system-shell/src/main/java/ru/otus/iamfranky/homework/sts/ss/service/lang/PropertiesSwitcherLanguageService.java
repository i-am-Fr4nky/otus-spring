package ru.otus.iamfranky.homework.sts.ss.service.lang;

import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.sts.sb.properties.ResourceFileProperties;
import ru.otus.iamfranky.homework.sts.ss.exception.LanguageNotSupportedException;
import ru.otus.iamfranky.homework.sts.ss.properties.LocalizationProperties;

import java.util.Locale;

@Service
public class PropertiesSwitcherLanguageService implements LanguageService {

    private final LocalizationProperties localizationProperties;
    private final ResourceFileProperties resourceFileProperties;

    public PropertiesSwitcherLanguageService(LocalizationProperties localizationProperties,
                                             ResourceFileProperties resourceFileProperties) throws LanguageNotSupportedException {

        this.localizationProperties = localizationProperties;
        this.resourceFileProperties = resourceFileProperties;

        checkLanguageSupportedAndSwitch(localizationProperties.getLocale());
    }

    private void checkLanguageSupportedAndSwitch(Locale locale) throws LanguageNotSupportedException {
        for (var language : localizationProperties.getLanguages()) {
            if (language.getLocale().equals(locale)) {
                localizationProperties.setLocale(locale);
                resourceFileProperties.setPath(language.getFile());
                return;
            }
        }
        throw new LanguageNotSupportedException(locale);
    }

    @Override
    public void switchLanguage(Locale locale) throws LanguageNotSupportedException {
        checkLanguageSupportedAndSwitch(locale);
    }
}
