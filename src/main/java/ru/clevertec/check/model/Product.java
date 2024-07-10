package main.java.ru.clevertec.check.model;

import java.util.Objects;

public class Product{
    Long id;
    String description;
    Double priceUSD;
    Long quantityInStock;
    boolean wholesaleProduct;

    public Product(Long id, String description, Double priceUSD, Long quantityInStock, boolean wholesaleProduct) {
        this.id = id;
        this.description = description;
        this.priceUSD = priceUSD;
        this.quantityInStock = quantityInStock;
        this.wholesaleProduct = wholesaleProduct;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (wholesaleProduct != product.wholesaleProduct) return false;
        if (!Objects.equals(id, product.id))
            return false;
        if (!Objects.equals(description, product.description))
            return false;
        if (!Objects.equals(priceUSD, product.priceUSD))
            return false;
        return Objects.equals(quantityInStock, product.quantityInStock);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (priceUSD != null ? priceUSD.hashCode() : 0);
        result = 31 * result + (quantityInStock != null ? quantityInStock.hashCode() : 0);
        result = 31 * result + (wholesaleProduct ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", priceUSD=").append(priceUSD);
        sb.append(", quantityInStock=").append(quantityInStock);
        sb.append(", wholesaleProduct=").append(wholesaleProduct);
        sb.append('}');
        return sb.toString();
    }
}
