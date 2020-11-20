package ru.otus.iamfranky.homework.sts.ss.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.iamfranky.homework.sts.sb.properties.AnswerProperties;
import ru.otus.iamfranky.homework.sts.sb.properties.LocalizationProperties;
import ru.otus.iamfranky.homework.sts.sb.properties.ResourceFileProperties;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageServiceImpl;
import ru.otus.iamfranky.homework.sts.sb.service.ui.ConsoleBasedUIService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@EnableConfigurationProperties({
        LocalizationProperties.class,
})
@RequiredArgsConstructor
public class UIConfig {

    private final MessageSource messageSource;
    private final LocalizationProperties localizationProperties;

    @Bean
    public UIService uiService(
            @Value("#{ T(java.lang.System).in}") InputStream in,
            @Value("#{ T(java.lang.System).out}") PrintStream out) {

        return new ConsoleBasedUIService(in, out);
    }

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl(messageSource, localizationProperties);
    }
}
