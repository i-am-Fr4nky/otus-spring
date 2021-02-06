package ru.otus.iamfranky.homework.sts.ss.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.iamfranky.homework.sts.sb.dao.QuestionDao;
import ru.otus.iamfranky.homework.sts.sb.dao.QuestionDaoCSV;
import ru.otus.iamfranky.homework.sts.sb.properties.AnswerProperties;
import ru.otus.iamfranky.homework.sts.sb.properties.ResourceFileProperties;
import ru.otus.iamfranky.homework.sts.sb.service.answers.AnswersService;
import ru.otus.iamfranky.homework.sts.sb.service.answers.AnswersServiceImpl;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamService;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamServiceLimitBased;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageServiceImpl;
import ru.otus.iamfranky.homework.sts.sb.service.ui.ConsoleBasedUIService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;
import ru.otus.iamfranky.homework.sts.ss.properties.LocalizationProperties;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@EnableConfigurationProperties({
        AnswerProperties.class,
        LocalizationProperties.class,
        ResourceFileProperties.class
})
@RequiredArgsConstructor
public class Config {

    @Bean
    public QuestionDao questionDao(ResourceFileProperties resourceFileProperties) {
        return new QuestionDaoCSV(resourceFileProperties);
    }

    @Bean
    public AnswersService answersService(QuestionDao questionDao, UIService uiService) {
        return new AnswersServiceImpl(questionDao, uiService);
    }

    @Bean
    public ExamService examService(AnswerProperties answerProperties, AnswersService answersService,
                                   UIService uiService, MessageService messageService) {

        return new ExamServiceLimitBased(answerProperties, answersService, uiService, messageService);
    }

    @Bean
    public UIService uiService(
            @Value("#{ T(java.lang.System).in}") InputStream in,
            @Value("#{ T(java.lang.System).out}") PrintStream out) {

        return new ConsoleBasedUIService(in, out);
    }

    @Bean
    public MessageService messageService(MessageSource messageSource, ru.otus.iamfranky.homework.sts.sb.properties.LocalizationProperties localizationProperties) {
        return new MessageServiceImpl(messageSource, localizationProperties);
    }
}
