package ru.otus.iamfranky.homework.service;

import ru.otus.iamfranky.homework.domain.Student;

public interface ExamService {

    boolean exam(Student student) throws Exception;
}