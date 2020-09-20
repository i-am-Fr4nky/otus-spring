package ru.otus.iamfranky.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.iamfranky.homework.service.exam.ExamRunner;

public class Main {

    public static final String CONTEXT_PATH = "/spring-context.xml";

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = null;

        try {
            context = new ClassPathXmlApplicationContext(CONTEXT_PATH);

            var examRunner = context.getBean(ExamRunner.class);
            examRunner.run();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
}