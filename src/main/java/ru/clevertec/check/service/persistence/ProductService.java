package main.java.ru.clevertec.check.service.persistence;

import main.java.ru.clevertec.check.model.Product;

import java.util.Map;

public interface ProductService {

    Map<Long, Product> getAll();

    Product findById(Long id);
}
