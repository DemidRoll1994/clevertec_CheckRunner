package main.java.ru.clevertec.check.model;

import java.util.Objects;

public class CheckPosition {

    private int quantity;
    private Product product;
    private double discount;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckPosition{");
        sb.append("quantity=").append(quantity);
        sb.append(", product=").append(product);
        sb.append(", discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckPosition position = (CheckPosition) o;

        if (quantity != position.quantity) return false;
        if (Double.compare(position.discount, discount) != 0)
            return false;
        return Objects.equals(product, position.product);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = quantity;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
