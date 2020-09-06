package ru.otus.iamfranky.homework.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.domain.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class QuestionDaoCSV implements QuestionDao {

    private static final String COMMA_DELIMITER = ",";
    private final String fileName;

    @Override
    public List<Question> findQuestion() throws Exception {
        var result = new ArrayList<Question>();

        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            var line = new String();
            while ((line = br.readLine()) != null) {
                var attributes = line.split(COMMA_DELIMITER);
                result.add(new Question(attributes[0], attributes[1]));
            }
        }
        return result;
    }
}