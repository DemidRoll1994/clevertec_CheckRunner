package main.java.ru.clevertec.check.dao;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Product;

import java.util.Map;

public interface ProductDao {
    public Map<Long, Product> getAll();
    public Product findById(Long id);
}
