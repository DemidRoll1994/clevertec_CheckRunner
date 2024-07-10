package main.java.ru.clevertec.check.dao.CSV;

import main.java.ru.clevertec.check.dao.ProductDao;
import main.java.ru.clevertec.check.io.CSVIO;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.parser.CSVFileParser;

import java.util.Map;

public class CSVProductDao implements ProductDao {

    private String CSVFilePath;

    public CSVProductDao(String CSVFilePath) {
        this.CSVFilePath = CSVFilePath;
    }
    @Override
    public Map<Long, Product> getAll() {
        CSVFileParser csvFileParser = new CSVFileParser();
        CSVIO csvio = new CSVIO();
        return csvFileParser.parseProducts(csvio.getLines(CSVFilePath));
    }

    @Override
    public Product findById(Long id) { //bad implementation
        return getAll().get(id);
    }

}
