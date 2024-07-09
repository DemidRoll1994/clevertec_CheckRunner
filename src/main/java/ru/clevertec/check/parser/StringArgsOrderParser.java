package main.java.ru.clevertec.check.parser;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.model.Order;

import java.util.*;

//start with SOLID principles breaking
public class StringArgsOrderParser implements OrderParser
{

    public StringArgsOrderParser() {
    }
//
//    public static RequestParserAndValidator validate(String[] args) {
//        if (args == null || args.length < 3) {
//            isValid = false;
//        }
//        if (args[args.length - 1].startsWith("balanceDebitCard=") || args[args.length - 1].startsWith("discountCard="))
//
//            return new RequestParserAndValidator();
//    }

    public Order parseOrder(String[] args) throws BadRequestException {
        if (args == null || args.length < 3) {
            throw new BadRequestException("can't parse arguments");
        }

        var order = new Order.Builder();

        Map<Long, Integer> products = new HashMap();
        String[] splitTempResult;
        List<String[]> splitProducts = new ArrayList<>();
        for (var arg : args) {
            if (arg.contains("=")) {
                splitTempResult = arg.split("=");
                try {
                    switch (splitTempResult[0]) {
                        case "discountCard" ->
                                order.discountCardNumber(Optional.of(
                                        Long.parseLong(splitTempResult[1])));
                        case "balanceDebitCard" ->
                                order.balanceDebitCard(
                                        Double.parseDouble(splitTempResult[1]));
                        default -> throw new BadRequestException("");
                    }
                } catch (Exception e) {
                    throw new BadRequestException("Can't parse some card");
                }
            } else if (arg.contains("-")) {
                splitTempResult = arg.split("-");
                splitProducts.add(splitTempResult);
//                if (splitTempResult.length == 2
//                        && splitTempResult[0].length() != 0
//                        && splitTempResult[1].length() != 0) {
                    long id;
                    int quantity;
                    try {
                        id = Long.parseLong(splitTempResult[0]);
                        quantity = Integer.parseInt(splitTempResult[1]);
                    } catch (Exception e) {
                        throw new BadRequestException("Products combination " +
                                "is invalid" + e.getMessage());
                    }
                    if (products.containsKey(id)) {
                        products.put(id, products.get(id) + quantity);
                    } else {
                        products.put(id, quantity);
                    }
                } else {
                    throw new BadRequestException("Products combination is invalid");
//                }
            }
        }
        order.products(products);
        return order.build();
//        List productQuantites = Arrays.stream(args).filter(i -> i.contains("-")).map(i -> i.split("-")).collect(Collectors.toList());
//
//
//        List cards = Arrays.stream(args).filter(i -> i.contains("=")).collect(Collectors.toList());
//
//        String balanceDebitCardString = args[args.length - 1];
//        if (!balanceDebitCardString.startsWith("balanceDebitCard="))
//            throw new BadRequestException(BALANCE_DEBIT_CARD_ERROR);
//        var tempResult = balanceDebitCardString.split("=");
//
//        try {
//            order.setBalanceDebitCard(Double.parseDouble(tempResult[1]));
//        } catch (Exception e) {
//            throw new BadRequestException(BALANCE_DEBIT_CARD_ERROR);
//        }
//
//
//        String discountCardNumber = args[args.length - 2];
//        if (!args[args.length - 1].startsWith("balanceDebitCard=") && !args[args.length - 1].substring(17).) {
//            result.setBalanceDebitCard();
//        } result.setDiscountCardNumber();
//
//
//        if (args[args.length - 1].startsWith("balanceDebitCard=") || args[args.length - 1].startsWith("discountCard="))
    }
//
//    public boolean isValid() {
//
//    }
//
//    public Map getResult() {
//
//    }
//
//    public static ErrorMessage getErrorMessage() {
//
//    }
//*/
}
