package ru.otus.iamfranky.homework.sts.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.iamfranky.homework.sts.sb.service.exam.ExamRunner;

@SpringBootApplication
public class StudentTestingSystemSpringBootApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(StudentTestingSystemSpringBootApplication.class, args);
		var examRunner = context.getBean(ExamRunner.class);
		examRunner.run();
	}

}
