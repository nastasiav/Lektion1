package org.my.edy.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.my.edy.model.Currency.RUB;
import static org.my.edy.model.Currency.USD;

class ProductTest {
    private Money money1;
    private Money money2;
    private Money money3;
    private Money money4;

    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;

    @BeforeEach
    void setUp() {
        money1 = new Money(RUB, BigInteger.valueOf(100L));
        money2 = new Money(RUB, BigInteger.valueOf(500L));
        money3 = new Money(RUB, BigInteger.valueOf(1000L));
        money4 = new Money(USD, BigInteger.valueOf(100L));

        product1 = new Product("1", "product1", money1, 1);
        product2 = new Product("2", "product2", money2, 2);
        product3 = new Product("3", "product3", money3, 2);
        product4 = new Product("4", "product4", money4, 1);
    }

    @Test
    void getId() {
        Assertions.assertEquals("1", product1.getId());
        Assertions.assertEquals("2", product2.getId());
        Assertions.assertEquals("3", product3.getId());
        Assertions.assertEquals("4", product4.getId());
    }

    @Test
    void getName() {
        Assertions.assertEquals("product1", product1.getName());
        Assertions.assertEquals("product2", product2.getName());
        Assertions.assertEquals("product3", product3.getName());
        Assertions.assertEquals("product4", product4.getName());
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(money1, product1.getPrice());
        Assertions.assertEquals(money2, product2.getPrice());
        Assertions.assertEquals(money3, product3.getPrice());
        Assertions.assertEquals(money4, product4.getPrice());
    }

    @Test
    void getCategory() {
        Assertions.assertEquals(1, product1.getCategory());
        Assertions.assertEquals(2, product2.getCategory());
        Assertions.assertEquals(2, product3.getCategory());
        Assertions.assertEquals(1, product4.getCategory());
    }

    @Test
    void changePrice() {
        Product newProduct = product1.changePrice(new Money(product1.getPrice().getCurrency(),
                BigInteger.valueOf(500L)));

        Assertions.assertNotEquals(newProduct, product1);
        Assertions.assertEquals(100L, product1.getPrice().getSum().longValue());
        Assertions.assertEquals(500L, newProduct.getPrice().getSum().longValue());
        Assertions.assertNotEquals(newProduct.getPrice().getSum().longValue(),
                product1.getPrice().getSum().longValue());
        Assertions.assertNotEquals(newProduct.getPrice(), product1.getPrice());
        Assertions.assertEquals(newProduct.getId(), product1.getId());
        Assertions.assertEquals(newProduct.getCategory(), product1.getCategory());
        Assertions.assertEquals(newProduct.getName(), product1.getName());
    }

    @Test
    void changeCategory() {
        Product newProduct = product1.changeCategory(6);

        Assertions.assertNotEquals(newProduct, product1);
        Assertions.assertEquals(1, product1.getCategory());
        Assertions.assertEquals(6, newProduct.getCategory());
        Assertions.assertNotEquals(newProduct.getCategory(), product1.getCategory());
        Assertions.assertEquals(newProduct.getPrice(), product1.getPrice());
        Assertions.assertEquals(newProduct.getId(), product1.getId());
        Assertions.assertEquals(newProduct.getName(), product1.getName());
    }
}