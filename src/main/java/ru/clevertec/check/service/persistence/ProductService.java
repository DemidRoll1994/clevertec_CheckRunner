package main.java.ru.clevertec.check.service.persistence;

import main.java.ru.clevertec.check.model.Product;

import java.util.Map;

public interface ProductService {

    public Map<Long, Product> getAll();

    public Product findById(Long id);
}
