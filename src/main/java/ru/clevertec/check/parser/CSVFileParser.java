package main.java.ru.clevertec.check.parser;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Product;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVFileParser {
    public Map<Long, DiscountCard> parseDiscountCards(List<String> cardsList) {
        if (cardsList != null && cardsList.size() > 1) {
            try {
                Map<Long, DiscountCard> discountCards = cardsList.stream()
                        .skip(1)
                        .map(i -> Stream.of(i.split(";"))
                                .collect(Collectors.toList()))
                        .filter(cardArray -> cardArray.size() == 3)
                        .collect(Collectors.toMap(card -> Long.parseLong(card.get(1)),
                                card -> new DiscountCard(Long.parseLong(card.get(0))
                                        , Long.parseLong(card.get(1))
                                        ,Double.parseDouble(card.get(2))/100)));
                return discountCards;
            } catch (Exception e) {
            }
        }
        return new HashMap<>();
    }

    public Map<Long, Product> parseProducts(List<String> productsList) {
        if (productsList != null && productsList.size() > 1) {
            try {
                Map<Long, Product> products = productsList.stream()
                        .skip(1)
                        .map(i -> Stream.of(i.split(";"))
                                .collect(Collectors.toList()))
                        .filter(productArray -> productArray.size() == 5)
                        .collect(Collectors.toMap(
                                product -> Long.parseLong(product.get(0)),
                                product -> new Product(
                                        Long.parseLong(product.get(0)),
                                        product.get(1),
                                        Double.parseDouble(product.get(2).replace(',', '.')),
                                        Long.parseLong(product.get(3)),
                                        "+".equals(product.get(4)))));

                return products;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return new HashMap<>();
    }
}
