package org.my.edy.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.my.edy.exception.MoneyException;
import org.my.edy.service.PriceValidator;

import java.math.BigInteger;

import static org.my.edy.model.Currency.RUB;
import static org.my.edy.model.Currency.USD;

class MoneyTest {

    private Money money1;
    private Money money2;
    private Money money3;
    private Money money4;

    @BeforeEach
    void setUp() {
        money1 = new Money(RUB, BigInteger.valueOf(100L));
        money2 = new Money(RUB, BigInteger.valueOf(500L));
        money3 = new Money(RUB, BigInteger.valueOf(1000L));
        money4 = new Money(USD, BigInteger.valueOf(100L));
    }

    @Test
    void getCurrency() {
        Assertions.assertEquals(RUB, money1.getCurrency());
        Assertions.assertEquals(RUB, money2.getCurrency());
        Assertions.assertEquals(RUB, money3.getCurrency());
        Assertions.assertEquals(USD, money4.getCurrency());
    }

    @Test
    void getSum() {
        Assertions.assertEquals(100L, money1.getSum().longValue());
        Assertions.assertEquals(500L, money2.getSum().longValue());
        Assertions.assertEquals(1000L, money3.getSum().longValue());
        Assertions.assertEquals(100L, money4.getSum().longValue());
    }

    @Test
    void createMoney_WithoutCurrency_ThrowsExpected() {
        Currency currency = null;
        BigInteger sum = BigInteger.valueOf(100L);

        MoneyException exception = Assertions.assertThrows(MoneyException.class,
                () -> new Money(currency, sum));

        Assertions.assertTrue(exception.getMessage()
                .contains("По заданным параметрам нельзя создать Money"));

    }

    @Test
    void createMoney_NegativeSum_ThrowsExpected() {
        Currency currency = RUB;
        BigInteger sum = BigInteger.valueOf(-100L);

        MoneyException exception = Assertions.assertThrows(MoneyException.class,
                () -> new Money(currency, sum));

        Assertions.assertTrue(exception.getMessage()
                .contains("По заданным параметрам нельзя создать Money"));

    }

    @Test
    void createMoney_WithoutSum_ThrowsExpected() {
        Currency currency = RUB;
        BigInteger sum = null;

        MoneyException exception = Assertions.assertThrows(MoneyException.class,
                () -> new Money(currency, sum));

        Assertions.assertTrue(exception.getMessage()
                .contains("По заданным параметрам нельзя создать Money"));

    }

    @Test
    void createMoney_WithoutAll_ThrowsExpected() {
        Currency currency = null;
        BigInteger sum = null;

        MoneyException exception = Assertions.assertThrows(MoneyException.class,
                () -> new Money(currency, sum));

        Assertions.assertTrue(exception.getMessage()
                .contains("По заданным параметрам нельзя создать Money"));

    }

    @Test
    void validatorShouldAcceptValidValues() {
        Currency currency = Currency.RUB;
        BigInteger sum = BigInteger.valueOf(500L);

        PriceValidator validator = (c, s) ->
                c != null && s != null && s.compareTo(BigInteger.ZERO) > 0;

        Assertions.assertTrue(validator.isValid(currency, sum));
    }

    @Test
    void validatorShouldRejectInvalidValues() {
        PriceValidator validator = (c, s) ->
                c != null && s != null && s.compareTo(BigInteger.ZERO) > 0;

        Assertions.assertFalse(validator.isValid(null, BigInteger.valueOf(100)));
        Assertions.assertFalse(validator.isValid(Currency.RUB, null));
        Assertions.assertFalse(validator.isValid(Currency.RUB, BigInteger.ZERO));
        Assertions.assertFalse(validator.isValid(Currency.RUB, BigInteger.valueOf(-50)));
    }
}