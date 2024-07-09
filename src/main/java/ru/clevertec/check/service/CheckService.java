package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.model.*;
import main.java.ru.clevertec.check.parser.OrderParser;
import main.java.ru.clevertec.check.parser.StringArgsOrderParser;
import main.java.ru.clevertec.check.service.persistence.CheckOutputService;
import main.java.ru.clevertec.check.service.persistence.DiscountCardService;
import main.java.ru.clevertec.check.service.persistence.ProductService;
import main.java.ru.clevertec.check.service.persistence.console.ConsoleCheckOutputService;
import main.java.ru.clevertec.check.service.persistence.csv.CSVCheckOutputService;
import main.java.ru.clevertec.check.service.persistence.csv.CSVDiscountCardService;
import main.java.ru.clevertec.check.service.persistence.csv.CSVProductService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CheckService {

    private OrderParser parser;
    private final String PRODUCT_FILE_PATH = "./src/main/resources/products.csv";
    private final String DISCOUNT_CARD_FILE_PATH = "./src/main/resources/discountCards.csv";
    private final String RESULT_FILE_PATH = "result.csv";
    private DiscountCardService discountCardService;
    private ProductService productService;
    private DiscountSystemService discountSystemService;
    private CheckOutputService checkOutputService1 =
            new CSVCheckOutputService(RESULT_FILE_PATH);
    private CheckOutputService checkOutputService2 = new ConsoleCheckOutputService();

    public void makeCheck(String[] args) {
        try {
            boolean isDone;
            parser = new StringArgsOrderParser();
            Order order = parser.parseOrder(args);
            validateOrder(order);
            discountCardService
                    = new CSVDiscountCardService(DISCOUNT_CARD_FILE_PATH);
            productService = new CSVProductService(PRODUCT_FILE_PATH);
            discountSystemService = new DiscountSystemService();
            checkOutputService1 = new CSVCheckOutputService(RESULT_FILE_PATH);
            checkOutputService2 = new ConsoleCheckOutputService();
            Check check = new Check();

            if (order.discountCardNumber().isPresent()) {
                check.setDiscountCard(Optional.ofNullable(discountCardService
                        .findByCardNumber(order.discountCardNumber().get())));
            }
            check.setLocalDateTime(getTimeStamp());
            check.setPositions(getPositions(order));
            discountSystemService.calculateDiscount(check);
            validateCheck(check, order);

            isDone = checkOutputService1.print(check)
                    && checkOutputService2.print(check);
            if (!isDone) {
                checkOutputService1.printErrorMessage(("Internal Server Error"));
            }
        } catch (NotEnoughMoneyException e) {
            checkOutputService1.printErrorMessage("NOT ENOUGH MONEY");
            checkOutputService2.printErrorMessage(e.getMessage());
        } catch (BadRequestException e) {
            checkOutputService1.printErrorMessage("BAD REQUEST");
            checkOutputService2.printErrorMessage(e.getMessage());
        } catch (Exception e) {
            checkOutputService1.printErrorMessage("INTERNAL SERVER ERROR");
            checkOutputService2.printErrorMessage(e.getMessage());
        }
    }

    private void validateOrder(Order order) throws BadRequestException {
        if (order.balanceDebitCard() == null)
            throw new BadRequestException("balanceDebitCard parameter is " +
                    "invalid");
    }

    private void validateCheck(Check check, Order order) throws BadRequestException {
        double totalPrice = 0.0;
        for (var position : check.getPositions()) {
            if (position.getProduct().getQuantityInStock()
                    < position.getQuantity()) {
                throw new BadRequestException("Not enough product on stock");
            }
            totalPrice += position.getQuantity()
                    * position.getProduct().getPriceUSD() -
                    Math.round(position.getDiscount()
                            * position.getProduct().getPriceUSD()
                            * position.getQuantity() * 100) / 100.0;
        }
        if (order.balanceDebitCard() < round(totalPrice))
            throw new NotEnoughMoneyException(String.format("NOT ENOUGH " +
                            "MONEY. debitCard balance= %.2f, total price=%.2f",
                    order.balanceDebitCard(), totalPrice));
    }

    private List<CheckPosition> getPositions(Order order) throws BadRequestException {
        List<CheckPosition> positions = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : order.productQuantites().entrySet()) {
            var checkPosition = new CheckPosition();
            Product product = productService.findById(entry.getKey());
            if (product == null) throw new BadRequestException(String.format(
                    "No product with ID %d found", entry.getKey()));
            checkPosition.setProduct(product);
            checkPosition.setQuantity(entry.getValue());
            positions.add(checkPosition);
        }
        return positions;
    }

    private LocalDateTime getTimeStamp() {
        var time = LocalDateTime.now();
        time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy;HH:mm:ss"));
        return time;
    }

    private double round(double value) {
        return Math.round(value * 100) / 100.0;
    }

}
