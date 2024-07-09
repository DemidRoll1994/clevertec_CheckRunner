package main.java.ru.clevertec.check.model;

public class Product{
    Long id;
    String description;
    Double priceUSD;
    Long quantityInStock;
    boolean wholesaleProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public boolean isWholesaleProduct() {
        return wholesaleProduct;
    }

    public void setWholesaleProduct(boolean wholesaleProduct) {
        this.wholesaleProduct = wholesaleProduct;
    }

    public Product(Long id, String description, Double priceUSD, Long quantityInStock, boolean wholesaleProduct) {
        this.id = id;
        this.description = description;
        this.priceUSD = priceUSD;
        this.quantityInStock = quantityInStock;
        this.wholesaleProduct = wholesaleProduct;
    }
}
