package main.java.ru.clevertec.check.dao;

import main.java.ru.clevertec.check.model.DiscountCard;

import java.util.Map;

public interface DiscountCardDao {
    Map <Long, DiscountCard> getAll();
    DiscountCard findByCardNumber(Long cardNumber);
}
