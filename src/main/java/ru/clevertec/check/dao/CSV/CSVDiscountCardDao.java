package main.java.ru.clevertec.check.dao.CSV;

import main.java.ru.clevertec.check.dao.DiscountCardDao;
import main.java.ru.clevertec.check.io.CSVIO;
import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.parser.CSVFileParser;

import java.util.Map;

public class CSVDiscountCardDao implements DiscountCardDao {
    private String CSVFilePath;
    private final double BASIC_DISCOUNT_AMOUNT = 0.02;

    public CSVDiscountCardDao(String CSVFilePath) {
        this.CSVFilePath = CSVFilePath;
    }

    @Override
    public Map<Long, DiscountCard> getAll() {
        CSVFileParser csvFileParser = new CSVFileParser();
        CSVIO csvio = new CSVIO();

        return csvFileParser.parseDiscountCards(csvio.getLines(CSVFilePath));
    }

    @Override
    public DiscountCard findByCardNumber(Long cardNumber) {
        DiscountCard discountCard = getAll().get(cardNumber);
        return discountCard != null ? discountCard : new DiscountCard
                .Builder(-1)
                .cardNumber(cardNumber)
                .discountAmount(BASIC_DISCOUNT_AMOUNT)
                .build();
    }
}
