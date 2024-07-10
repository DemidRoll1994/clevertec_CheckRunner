package main.java.ru.clevertec.check.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Check {
    private LocalDateTime localDateTime;
    private List<CheckPosition> positions;
    private Optional<DiscountCard> discountCard = Optional.empty();


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<CheckPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<CheckPosition> positions) {
        this.positions = positions;
    }

    public Optional<DiscountCard> getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(Optional<DiscountCard> discountCard) {
        this.discountCard = discountCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (!localDateTime.equals(check.localDateTime)) return false;
        if (!positions.equals(check.positions)) return false;
        return discountCard.equals(check.discountCard);
    }

    @Override
    public int hashCode() {
        int result = localDateTime.hashCode();
        result = 31 * result + positions.hashCode();
        result = 31 * result + discountCard.hashCode();
        return result;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Check{");
        sb.append("localDateTime=").append(localDateTime);
        sb.append(", positions=").append(positions);
        sb.append(", discountCard=").append(discountCard);
        sb.append('}');
        return sb.toString();
    }
}
