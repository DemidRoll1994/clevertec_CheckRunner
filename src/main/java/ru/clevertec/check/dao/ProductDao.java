package main.java.ru.clevertec.check.dao;

import main.java.ru.clevertec.check.model.Product;

import java.util.Map;

public interface ProductDao {
    Map<Long, Product> getAll();
    Product findById(Long id);
}
