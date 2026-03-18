package org.my.edy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.my.edy.exception.NotFoundProduct;
import org.my.edy.model.Money;
import org.my.edy.model.Product;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.my.edy.model.Currency.RUB;
import static org.my.edy.model.Currency.USD;

class ProductCatalogTest {
    private Money money1;
    private Money money2;
    private Money money3;
    private Money money4;

    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;

    ProductCatalog catalog;

    List<Product> products;

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

        catalog = new ProductCatalog(new ProductComparator());
        catalog.addProduct(product1);
        catalog.addProduct(product2);
        catalog.addProduct(product3);
        catalog.addProduct(product4);

        products = catalog.getAllProducts();
    }

    @Test
    void testImmutableListInCatalog() {
        Exception removed = Assertions.assertThrows(Exception.class,
                () -> products.removeFirst());
    }

    @Test
    void getAllProducts() {
        Assertions.assertTrue(List.of(product1, product2, product3, product4)
                .containsAll(products));
    }

    @Test
    void addProduct() {
        Product newProduct = new Product("10", "productNEW", money2, 1);
        catalog.addProduct(newProduct);

        Assertions.assertTrue(catalog.getAllProducts().contains(newProduct));
    }

    @Test
    void findById() {
        Optional<Product> finder = catalog.findById("1");

        Assertions.assertTrue(finder.isPresent());
        Assertions.assertEquals(product1, finder.get());
        Assertions.assertEquals(product1.getId(), finder.get().getId());
    }

    @Test
    void findById_NotFound() {
        Optional<Product> finder = catalog.findById("100");

        Assertions.assertFalse(finder.isPresent());
    }

    @Test
    void findByCategory() {
        List<Product> finder = catalog.findByCategory(1);

        Assertions.assertFalse(finder.isEmpty());
        Assertions.assertEquals(2, finder.size());
        Assertions.assertTrue(finder.contains(product1));
        Assertions.assertTrue(finder.contains(product4));
        Assertions.assertFalse(finder.contains(product2));
        Assertions.assertFalse(finder.contains(product3));
    }

    @Test
    void findByCategory_NotFound() {
        List<Product> finder = catalog.findByCategory(6);

        Assertions.assertTrue(finder.isEmpty());
    }

    @Test
    void findByPrice() {
        Money rub100 = new Money(RUB, BigInteger.valueOf(100L));
        Money usd100 = new Money(USD, BigInteger.valueOf(100L));

        List<Product> products100rub = catalog.findByPrice(rub100);
        List<Product> products100usd = catalog.findByPrice(usd100);

        Assertions.assertFalse(products100rub.isEmpty());
        Assertions.assertFalse(products100usd.isEmpty());
        Assertions.assertEquals(1, products100rub.size());
        Assertions.assertEquals(1, products100usd.size());
        Assertions.assertTrue(products100rub.contains(product1));
        Assertions.assertTrue(products100usd.contains(product4));

    }

    @Test
    void findByPriceRange() {
        Money min = new Money(RUB, BigInteger.valueOf(100L));
        Money max400 = new Money(RUB, BigInteger.valueOf(400L));
        Money max1000 = new Money(RUB, BigInteger.valueOf(1000L));

        List<Product> products100_400 = catalog.findByPriceRange(min, max400);
        List<Product> products100_1000 = catalog.findByPriceRange(min, max1000);

        Assertions.assertFalse(products100_400.isEmpty());
        Assertions.assertFalse(products100_1000.isEmpty());
        Assertions.assertEquals(1, products100_400.size());
        Assertions.assertEquals(3, products100_1000.size());
        Assertions.assertTrue(products100_400.contains(product1));
        Assertions.assertTrue(products100_1000
                .containsAll(List.of(product1, product2, product3)));
    }


    @Test
    void testProductReverseIterator() {
        ProductReverseIterator iterator = new ProductReverseIterator(catalog.getProductCatalog());
        int index = products.size() - 1;
        int count = 0;

        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            Product expectedProduct = products.get(index);

            Assertions.assertEquals(expectedProduct.getId(),
                    currentProduct.getId());
            Assertions.assertEquals(expectedProduct.getName(),
                    currentProduct.getName());
            Assertions. assertEquals(expectedProduct.getPrice(),
                    currentProduct.getPrice());

            index--;
            count++;
        }

        Assertions.assertEquals(products.size(), count);
        Assertions.assertFalse(iterator.hasNext());

        Assertions.assertThrows(NotFoundProduct.class,
                iterator::next);
    }

    @Test
    void testEmptyCollection() {
        ProductCatalog emptyCatalog = new ProductCatalog(new ProductComparator());

        ProductReverseIterator iterator =
                new ProductReverseIterator(emptyCatalog.getProductCatalog());

        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertThrows(NotFoundProduct.class,
                iterator::next);
    }
}