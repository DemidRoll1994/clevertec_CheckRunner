package main.java.ru.clevertec.check.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String s) {
        super(s);
    }
}