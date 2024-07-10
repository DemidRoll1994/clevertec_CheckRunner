package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.service.CheckService;

public class CheckRunner {
    public static void main(String[] args) {
        new CheckService().makeCheck(args);
    }
}