package main.java.ru.clevertec.check.service.persistence.csv;

import main.java.ru.clevertec.check.dao.CSV.CSVProductDao;
import main.java.ru.clevertec.check.dao.ProductDao;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.service.persistence.ProductService;

import java.util.Map;

public class CSVProductService implements ProductService {

    ProductDao productDao;
    Map<Long, Product> cashedProducts;

    public CSVProductService(String filePath) {
        productDao = new CSVProductDao(filePath);
    }

    @Override
    public Map<Long, Product> getAll() {
        cashedProducts = productDao.getAll();
        return productDao.getAll();
    }

    @Override
    public Product findById(Long id) {
        if (cashedProducts == null) cashedProducts = productDao.getAll();

        return cashedProducts.get(id);
    }
}
