package main.java.ru.clevertec.check.model;

import main.java.ru.clevertec.check.parser.OrderParser;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record Order(Map<Long, Integer> productQuantites,
                    Optional<Long> discountCardNumber ,
                    Double balanceDebitCard,
                    Optional<String> PathToSaveFile) {

    public static final class Builder {

        Map<Long, Integer> products = new HashMap<>();
        Optional<Long> discountCardNumber = Optional.empty();
        Double balanceDebitCard = null;
        Optional<String> PathToSaveFile = Optional.empty();

        public Builder() {
        }

        public Builder products(Map<Long, Integer> products) {
            this.products = products;
            return this;
        }

        public Builder discountCardNumber(Optional<Long> discountCardNumber) {
            this.discountCardNumber = discountCardNumber;
            return this;
        }

        public Builder balanceDebitCard(double balanceDebitCard) {
            this.balanceDebitCard = balanceDebitCard;
            return this;
        }

        public Builder PathToSaveFile(Optional<String> PathToSaveFile) {
            this.PathToSaveFile = PathToSaveFile;
            return this;
        }

        public Order build() {
            return new Order(products, discountCardNumber,
                    balanceDebitCard, PathToSaveFile);
        }
    }
}
