package ru.otus.iamfranky.homework.sts.sb.service.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleBasedUIService implements UIService {

    private final Scanner scanner;
    public final PrintStream out;

    public ConsoleBasedUIService(
            @Value("#{ T(java.lang.System).in}") InputStream in,
            @Value("#{ T(java.lang.System).out}") PrintStream out) {

        this.scanner = new Scanner(in);
        this.out = out;
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
