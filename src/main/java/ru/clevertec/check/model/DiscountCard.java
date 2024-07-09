package main.java.ru.clevertec.check.model;

public record DiscountCard(long id, long cardNumber, double discountAmount) {
    public static final class Builder {

        long id;
        long cardNumber;
        double discountAmount;

        public Builder(long id) {
            this.id = id;
        }

        public Builder cardNumber(long cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder discountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public DiscountCard build() {
            return new DiscountCard(id, cardNumber, discountAmount);
        }
    }
}
