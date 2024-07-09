package main.java.ru.clevertec.check.service.persistence.csv;

import main.java.ru.clevertec.check.io.CSVIO;
import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.service.persistence.CheckOutputService;

import java.time.format.DateTimeFormatter;

public class CSVCheckOutputService implements CheckOutputService {
    private CSVIO csvio = new CSVIO();
    private String pathToPrint;

    public CSVCheckOutputService(String pathToPrint) {
        this.pathToPrint = pathToPrint;
    }

    @Override
    public boolean print(Check check) {
        return csvio.writeFile(pathToPrint, prepareToPrintInCSV(check));
    }

    @Override
    public void printErrorMessage(String message) {
        csvio.writeFile("result.csv",
                String.format("ERROR\n%s", message));
    }

    private String prepareToPrintInCSV(Check check) {
        StringBuilder builder = new StringBuilder();
        builder.append("Date;Time\n");
        builder.append(check.getLocalDateTime()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy;HH:mm:ss")));
        builder.append("\n\n");
        builder.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");
        double totalPrice = 0.0,
                totalDiscount = 0.0,
                price,
                discount,
                total;
        for (var position : check.getPositions()) {
            builder.append(position.getQuantity()).append(";");
            builder.append(position.getProduct().getDescription()).append(";");
            price = position.getProduct().getPriceUSD();
            builder.append(price).append("$;");
            discount = round(position.getDiscount() * price
                    * position.getQuantity());
            totalDiscount += discount;
            builder.append(discount).append("$;");
            total = price * position.getQuantity();
            totalPrice += total;
            builder.append(total).append("$\n");
        }
        builder.append("\n");
        if (check.getDiscountCard().isPresent()) {
            builder.append("DISCOUNT CARD; DISCOUNT PERCENTAGE\n");
            builder.append(check.getDiscountCard().get().cardNumber())
                    .append(";")
                    .append(check.getDiscountCard().get().discountAmount() * 100)
                    .append("%\n\n");
        }

        builder.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
        builder.append(round(totalPrice)).append("$;")
                .append(round(totalDiscount)).append("$;")
                .append(round(totalPrice - totalDiscount)).append("$");
        return builder.toString();
    }

    private double round(double value) {
        return Math.round(value * 100) / 100.0;
    }
}
