package main.java.ru.clevertec.check.dao;

import main.java.ru.clevertec.check.model.DiscountCard;

import java.util.Map;

public interface DiscountCardDao {
    public Map <Long, DiscountCard> getAll();
    public DiscountCard findByCardNumber(Long cardNumber);
}
