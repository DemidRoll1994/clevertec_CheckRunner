package main.java.ru.clevertec.check.parser;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.model.Order;

public interface OrderParser {
    public Order parseOrder(String[] args) throws BadRequestException;
}
