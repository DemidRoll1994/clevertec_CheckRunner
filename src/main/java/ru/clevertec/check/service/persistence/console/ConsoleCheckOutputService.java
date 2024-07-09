package main.java.ru.clevertec.check.service.persistence.console;

import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.service.persistence.CheckOutputService;

import java.time.format.DateTimeFormatter;

public class ConsoleCheckOutputService implements CheckOutputService {
    @Override
    public boolean print(Check check) {
        System.out.println((prepareToPrintOnConsole(check)));
        return true;
    }

    @Override
    public void printErrorMessage(String message) {
        System.out.printf("ERROR\n%s", message);
    }

    private String prepareToPrintOnConsole(Check check) {
        StringBuilder builder = new StringBuilder();
        builder.append("Date\tTime\n");
        builder.append(check.getLocalDateTime()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy\tHH:mm:ss")));
        builder.append("\n\n");
        builder.append("QTY\tDESCRIPTION\tPRICE\tDISCOUNT\tTOTAL\n");
        double totalPrice = 0.0,
                totalDiscount = 0.0,
                price,
                discount,
                total;
        for (var position : check.getPositions()) {
            builder.append(position.getQuantity()).append("\t");
            builder.append(position.getProduct().getDescription()).append("\t");
            price = position.getProduct().getPriceUSD();
            builder.append(price).append("$\t");
            discount = round(position.getDiscount() * price
                    * position.getQuantity());
            totalDiscount += discount;
            builder.append(discount).append("$\t");
            total = price * position.getQuantity();
            totalPrice += total;
            builder.append(total).append("$\n");
        }
        builder.append("\n");
        if (check.getDiscountCard().isPresent()) {
            builder.append("DISCOUNT CARD; DISCOUNT PERCENTAGE\n");
            builder.append(check.getDiscountCard().get().cardNumber())
                    .append("\t")
                    .append(check.getDiscountCard().get().discountAmount() * 100)
                    .append("%\n\n");
        }

        builder.append("TOTAL PRICE\tTOTAL DISCOUNT\tTOTAL WITH DISCOUNT\n");
        builder.append(round(totalPrice)).append("$\t")
                .append(round(totalDiscount)).append("$\t")
                .append(round(totalPrice - totalDiscount)).append("$");
        return builder.toString();
    }

    private double round(double value) {
        return Math.round(value * 100) / 100.0;
    }
}
