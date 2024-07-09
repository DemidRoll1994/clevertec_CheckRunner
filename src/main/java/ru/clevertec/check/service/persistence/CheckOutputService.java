package main.java.ru.clevertec.check.service.persistence;

import main.java.ru.clevertec.check.model.Check;

public interface CheckOutputService {
    boolean print(Check check);
    void printErrorMessage(String message);
}
