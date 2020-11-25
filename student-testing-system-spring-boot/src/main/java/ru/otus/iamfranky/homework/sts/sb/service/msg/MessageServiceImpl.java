package ru.otus.iamfranky.homework.sts.sb.service.msg;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.sts.sb.properties.LocalizationProperties;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(LocalizationProperties.class)
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final LocalizationProperties localizationProperties;

    @Override
    public String getMsg(String propertyPath) {
        return messageSource.getMessage(propertyPath, null, localizationProperties.getLocale());
    }

    @Override
    public String getMsg(String propertyPath, Object... param) {
        return messageSource.getMessage(propertyPath, param, localizationProperties.getLocale());
    }

}
