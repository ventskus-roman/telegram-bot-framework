package by.roman.ventskus.telegram;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.entity.User;
import by.roman.ventskus.telegram.framework.entity.request.Request;

import java.util.Scanner;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        Framework framework = new Framework();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            framework.process(new Request(new User("firstUser"), message));
        }
    }
}
