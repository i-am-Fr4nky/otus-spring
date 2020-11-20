package ru.otus.iamfranky.homework.sts.ss.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

@Configuration
@EnableConfigurationProperties({
        AnswerProperties.class,
        ResourceFileProperties.class
})
@RequiredArgsConstructor
public class TestingConfig {

    private final AnswerProperties answerProperties;
    private final ResourceFileProperties resourceFileProperties;

    private final UIService uiService;
    private final MessageService messageService;

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoCSV(resourceFileProperties);
    }

    @Bean
    public AnswersService answersService() {
        return new AnswersServiceImpl(questionDao(), uiService);
    }

    @Bean
    public ExamService examService() {
        return new ExamServiceLimitBased(answerProperties, answersService(), uiService, messageService);
    }
}
