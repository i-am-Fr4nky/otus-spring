package ru.otus.iamfranky.homework.sts.sb.service.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.iamfranky.homework.sts.sb.service.msg.MessageService;
import ru.otus.iamfranky.homework.sts.sb.service.student.StudentService;
import ru.otus.iamfranky.homework.sts.sb.service.ui.UIService;

@Component
@RequiredArgsConstructor
public class ExamRunnerImpl implements ExamRunner, ExamMsgPaths {

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
            e.printStackTrace();
            uiService.inform(messageService.getMsg(ERROR_MSG, e.getMessage()));
        }
    }

}
