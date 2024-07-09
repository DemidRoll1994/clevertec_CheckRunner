package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Order;
import main.java.ru.clevertec.check.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DiscountSystemService {

    public double calculateDiscount(Optional<DiscountCard> discountCard,
                                    boolean isWholesaleProduct, int count) {
        double resultDiscount = 0;

        if (isWholesaleProduct && count >= 5) resultDiscount = 0.1;
        else if (discountCard.isPresent()) {
            resultDiscount = discountCard.get().discountAmount();
        }
        return resultDiscount;
    }

    public Check calculateDiscount(Check check) {
        var discountCard = check.getDiscountCard();
        check.setPositions(check.getPositions().stream()
                .peek(position ->
                        position.setDiscount(
                                calculateDiscount(discountCard,
                                        position.getProduct().isWholesaleProduct(),
                                        position.getQuantity())))
                .collect(Collectors.toList()));
        return check;
    }
}
