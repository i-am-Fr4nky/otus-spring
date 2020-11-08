package ru.otus.iamfranky.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.iamfranky.homework.service.exam.ExamRunner;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var examRunner = context.getBean(ExamRunner.class);
        examRunner.run();
    }
}