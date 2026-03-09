package org.my.edy.model;

import org.my.edy.exception.MoneyException;
import org.my.edy.service.PriceValidator;

import java.math.BigInteger;
import java.util.Objects;

public final class Money {
    private final Currency currency;
    private final BigInteger sum;

    PriceValidator validator = (currency, sum) ->
                    currency != null
                    && sum != null
                    && sum.compareTo(BigInteger.ZERO) > 0;

    public Money(Currency currency, BigInteger sum) {
        if (!validator.isValid(currency, sum))
            throw new MoneyException("По заданным параметрам нельзя создать Money: " + currency + " " + sum);
        this.currency = currency;
        this.sum = sum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigInteger getSum() {
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return currency == money.currency && Objects.equals(sum, money.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, sum);
    }

    @Override
    public String toString() {
        return "Money{" +
                "currency=" + currency +
                ", sum=" + sum +
                '}';
    }
}
