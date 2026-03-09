package org.my.edy.service;

import org.my.edy.model.Currency;
import org.my.edy.model.Money;

import java.math.BigInteger;

public interface PriceValidator {
    boolean isValid(Currency currency, BigInteger sum);
}
