package ru.otus.iamfranky.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.iamfranky.homework.service.ExamService;
import ru.otus.iamfranky.homework.service.StudentService;

public class Main {

    public static final String CONTEXT_PATH = "/spring-context.xml";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        try {
            context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
            context.refresh();

            var app = new App(context.getBean(StudentService.class),
                    context.getBean(ExamService.class));

            app.start();
        } finally {
            context.close();
        }
    }
}