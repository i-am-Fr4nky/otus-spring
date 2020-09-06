package ru.otus.iamfranky.homework.service;

import ru.otus.iamfranky.homework.domain.Student;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {

    private static final String NAME_MSG = "Please enter your name amd type Enter:";
    private static final String SURNAME_MSG = "Please enter your surname amd type Enter:";

    @Override
    public Student getStudent() {
        Scanner in = new Scanner(System.in);

        System.out.println(NAME_MSG);
        String name = in.nextLine();

        System.out.println(SURNAME_MSG);
        String surname = in.nextLine();

        return new Student(name, surname);
    }
}