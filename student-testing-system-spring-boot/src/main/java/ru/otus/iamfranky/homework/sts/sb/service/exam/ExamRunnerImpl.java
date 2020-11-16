package ru.otus.iamfranky.homework.sts.sb.service.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.student.StudentService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

@Component
@RequiredArgsConstructor
public class ExamRunnerImpl implements ExamRunner {

    private static final String START_MSG = "sts.sb.msg.app.start";
    private static final String ERROR_MSG = "sts.sb.msg.app.error";
    private static final String SUCCESS_MSG = "sts.sb.msg.app.end.success";
    private static final String FAILED_MSG = "sts.sb.msg.app.end.fail";

    private final StudentService studentService;
    private final ExamService examService;
    private final UIService uiService;
    private final MessageService messageService;

    public void run() {
        try {
            uiService.inform(messageService.getMsg(START_MSG));

            var student = studentService.getStudent();
            var result = examService.exam(student);
            var msg = result ? messageService.getMsg(SUCCESS_MSG) : messageService.getMsg(FAILED_MSG);
            uiService.inform(msg);

        } catch (Exception e) {
            uiService.inform(messageService.getMsg(ERROR_MSG, e.getMessage()));
            e.printStackTrace();
        }
    }

}
