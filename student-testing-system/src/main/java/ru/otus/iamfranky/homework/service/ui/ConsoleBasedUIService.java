package ru.otus.iamfranky.homework.service.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleBasedUIService implements UIService {

    private final Scanner scanner;
    public final PrintStream out;

    public ConsoleBasedUIService() {
        this.scanner = new Scanner(System.in);
        this.out = System.out;
    }

    @Override
    public void inform(String message) {
        this.out.println(message);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

}
