package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.service.CheckService;

public class CheckRunner {
    public static void main(String[] args) {
        CheckService checkService = new CheckService();
        checkService.makeCheck(args);
//
//        System.out.println(args);
//
//        var array = Arrays.stream(args)
//                .filter(i -> i.contains("-"))
//                .map(i -> i.split("-"))
//                .filter(i -> i.length == 2 && !i[0].isEmpty() && !i[1].isEmpty())
//                .collect(Collectors.groupingBy(
//                        listElement -> listElement[0],
//                        Collectors.summingInt(listElement -> Integer.parseInt(listElement[1]))
//                ));
//        //.collect(Collectors.toList());
//        array.size();
        //.forEach(System.out::println);

    }
}