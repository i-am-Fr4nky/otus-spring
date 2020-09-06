package ru.otus.iamfranky.homework;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.service.ExamService;
import ru.otus.iamfranky.homework.service.StudentService;

@RequiredArgsConstructor
public class App {

    private static final String START_MSG = "Starting the app...";
    private static final String SUCCESS_MSG = "Success, congratulations!";
    private static final String FAILED_MSG = "Fail, maybe later";

    private final StudentService studentService;
    private final ExamService examService;

    public void start() {
        try {
            System.out.println(START_MSG);
            var student = studentService.getStudent();
            var result = examService.exam(student);
            System.out.println(result ? SUCCESS_MSG : FAILED_MSG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
