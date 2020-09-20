package ru.otus.iamfranky.homework.service.exam;

import lombok.RequiredArgsConstructor;
import ru.otus.iamfranky.homework.service.student.StudentService;
import ru.otus.iamfranky.homework.service.ui.UIService;

@RequiredArgsConstructor
public class ExamRunner {

    private static final String START_MSG = "Starting the app...";
    private static final String ERROR_MSG = "Application error: %s";
    private static final String SUCCESS_MSG = "Success, congratulations!";
    private static final String FAILED_MSG = "Fail, maybe later";

    private final StudentService studentService;
    private final ExamService examService;
    private final UIService uiService;

    public void run() {
        try {
            uiService.inform(START_MSG);
            var student = studentService.getStudent();
            var result = examService.exam(student);
            var msg = result ? SUCCESS_MSG : FAILED_MSG;
            uiService.inform(msg);

        } catch (Exception e) {
            uiService.inform(String.format(ERROR_MSG, e.getMessage()));
            e.printStackTrace();
        }
    }

}
