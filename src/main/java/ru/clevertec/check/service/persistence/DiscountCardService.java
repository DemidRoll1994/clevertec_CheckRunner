package main.java.ru.clevertec.check.service.persistence;

import main.java.ru.clevertec.check.model.DiscountCard;

import java.util.Map;

public interface DiscountCardService {

    public Map<Long, DiscountCard> getAll();
    public DiscountCard findByCardNumber(Long cardNumber);
}
