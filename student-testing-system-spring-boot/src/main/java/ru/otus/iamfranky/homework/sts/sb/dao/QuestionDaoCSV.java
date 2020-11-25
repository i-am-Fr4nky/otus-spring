package ru.otus.iamfranky.homework.sts.sb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;
import ru.otus.iamfranky.homework.sts.sb.domain.Question;
import ru.otus.iamfranky.homework.sts.sb.exception.QuestionsReadingException;
import ru.otus.iamfranky.homework.sts.sb.properties.ResourceFileProperties;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@EnableConfigurationProperties(ResourceFileProperties.class)
public class QuestionDaoCSV implements QuestionDao {

    private final ResourceFileProperties resourceProperties;

    @Override
    public List<Question> findQuestion() throws QuestionsReadingException {
        var result = new ArrayList<Question>();

        try (var inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(resourceProperties.getPath());
             var br = new BufferedReader(new InputStreamReader(inputStream))) {

            var line = new String();
            while ((line = br.readLine()) != null) {
                var attributes = line.split(resourceProperties.getDelimiter());
                result.add(new Question(attributes[0], attributes[1]));
            }
            return result;

        } catch (Exception e) {
            throw new QuestionsReadingException(e);
        }
    }
}