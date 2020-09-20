package ru.otus.iamfranky.homework.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.domain.Question;
import ru.otus.iamfranky.homework.exception.QuestionsReadingException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoCSV implements QuestionDao {

    private static final String COMMA_DELIMITER = ",";
    private final String fileName;

    @Override
    public List<Question> findQuestion() throws QuestionsReadingException {
        var result = new ArrayList<Question>();

        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            var line = new String();
            while ((line = br.readLine()) != null) {
                var attributes = line.split(COMMA_DELIMITER);
                result.add(new Question(attributes[0], attributes[1]));
            }
            return result;

        } catch (Exception e) {
            throw new QuestionsReadingException(e);
        }
    }
}