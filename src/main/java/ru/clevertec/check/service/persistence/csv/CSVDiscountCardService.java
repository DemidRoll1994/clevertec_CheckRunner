package main.java.ru.clevertec.check.service.persistence.csv;

import main.java.ru.clevertec.check.dao.CSV.CSVDiscountCardDao;
import main.java.ru.clevertec.check.dao.DiscountCardDao;
import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.service.persistence.DiscountCardService;

import java.util.Map;

public class CSVDiscountCardService implements DiscountCardService {
    DiscountCardDao discountCardDao;
    public CSVDiscountCardService(String filePath) {
        discountCardDao = new CSVDiscountCardDao(filePath);
    }

    @Override
    public Map<Long, DiscountCard> getAll() {
        return discountCardDao.getAll();
    }

    @Override
    public DiscountCard findByCardNumber(Long cardNumber) {
        return discountCardDao.findByCardNumber(cardNumber);
    }
}
