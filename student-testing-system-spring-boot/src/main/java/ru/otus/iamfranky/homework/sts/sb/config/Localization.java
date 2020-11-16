package ru.otus.iamfranky.homework.sts.sb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@EnableConfigurationProperties(LocalizationProperties.class)
public class Localization {

    @Bean
    public MessageSource messageSource(
            @Value("${student.testing.system.localization.encoding:null}") String encoding,
            @Value("${student.testing.system.localization.bundle.path:null}") String bundlePath) {

        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(bundlePath);
        ms.setDefaultEncoding(encoding);
        return ms;
    }

}
