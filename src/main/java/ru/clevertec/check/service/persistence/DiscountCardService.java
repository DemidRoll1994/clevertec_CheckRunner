package main.java.ru.clevertec.check.service.persistence;

import main.java.ru.clevertec.check.model.DiscountCard;

import java.util.Map;

public interface DiscountCardService {

    Map<Long, DiscountCard> getAll();
    DiscountCard findByCardNumber(Long cardNumber);
}
