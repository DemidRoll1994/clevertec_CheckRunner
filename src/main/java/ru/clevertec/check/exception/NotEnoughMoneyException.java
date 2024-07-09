package main.java.ru.clevertec.check.exception;

public class NotEnoughMoneyException extends BadRequestException {
    public NotEnoughMoneyException(String s) {
        super(s);
    }
}
