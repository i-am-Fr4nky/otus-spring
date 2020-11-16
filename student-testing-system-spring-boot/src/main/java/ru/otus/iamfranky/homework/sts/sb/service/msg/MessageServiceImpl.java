package ru.otus.iamfranky.homework.sts.sb.service.msg;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.sts.sb.config.LocalizationProperties;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final LocalizationProperties localizationProperties;

    @Override
    public String getMsg(String propertyPath) {
        return messageSource.getMessage(propertyPath, null, localizationProperties.getLocale());
    }

    @Override
    public String getMsg(String propertyPath, Object param) {
        return messageSource.getMessage(propertyPath, new Object[]{param}, localizationProperties.getLocale());
    }

    @Override
    public String getMsg(String propertyPath, Object[] params) {
        return messageSource.getMessage(propertyPath, params, localizationProperties.getLocale());
    }
}
