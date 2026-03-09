package org.my.edy;

import org.my.edy.exception.MoneyException;
import org.my.edy.exception.NotFoundProduct;
import org.my.edy.model.Money;
import org.my.edy.model.Product;
import org.my.edy.service.ProductCatalog;
import org.my.edy.service.ProductComparator;
import org.my.edy.service.ProductReverseIterator;

import java.math.BigInteger;
import java.util.*;

import static org.my.edy.model.Currency.RUB;
import static org.my.edy.model.Currency.USD;

public class Main {

    private final static String BEGIN_RED = "\u001B[31m";
    private final static String END_RED = "\u001B[0m";
    private final static String BEGIN_GREEN = "\u001B[32m";
    private final static String END_GREEN = "\u001B[0m";
    private final static String BEGIN_BLUE = "\u001B[36m";
    private final static String END_BLUE = "\u001B[0m";

    public static void main(String[] args) {
        Money money1 = new Money(RUB, BigInteger.valueOf(100L));
        Money money2 = new Money(RUB, BigInteger.valueOf(500L));
        Money money3 = new Money(RUB, BigInteger.valueOf(1000L));
        Money money4 = new Money(USD, BigInteger.valueOf(100L));

        System.out.println(BEGIN_RED + "ТЕСТ MONEY" + END_RED);
        testMoney();

        Product product1 = new Product("1", "product1", money1, 1);
        Product product2 = new Product("2", "product2", money2, 2);
        Product product3 = new Product("3", "product3", money3, 2);
        Product product4 = new Product("4", "product4", money4, 1);

        System.out.println(BEGIN_RED + "ТЕСТ ПРОДУКТА" + END_RED);
        testChangePrice(product1, 500L);
        testChangeCategory(product1, 6);


        System.out.println(BEGIN_RED + "ТЕСТ КАТАЛОГА" + END_RED);
        ProductCatalog catalog = new ProductCatalog(new ProductComparator());
        catalog.addProduct(product1);
        catalog.addProduct(product2);
        catalog.addProduct(product3);
        catalog.addProduct(product4);

        List<Product> products = catalog.getAllProducts();

        System.out.println(products);

        testImmutableListInCatalog(products);
        testFindById(catalog);
        testFindByCategory(catalog);
        testFindByPriceRange(catalog);
        testFindByPrice(catalog);

        testProductIterator(catalog);
        testProductReverseIterator(catalog);

    }

    private static void testProductIterator(ProductCatalog catalog) {
        System.out.println(BEGIN_GREEN + "ТЕСТ ITERATOR" + END_GREEN);
        for (Product p : catalog.getProductCatalog().keySet())
            System.out.println(p);
    }

    private static void testProductReverseIterator(ProductCatalog catalog) {
        System.out.println(BEGIN_GREEN + "ТЕСТ ProductReverseIterator" + END_GREEN);
        ProductReverseIterator iterator =
                new ProductReverseIterator(catalog.getProductCatalog());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void testMoney() {
        try {
            Money money = new Money(null, BigInteger.valueOf(100L));
        } catch (MoneyException e) {
            System.out.println("Нелья создать объект: null, BigInteger.valueOf(100L)");
        }
        try {
            Money money = new Money(RUB, BigInteger.valueOf(-100L));
        } catch (MoneyException e) {
            System.out.println("Нелья создать объект: RUB, BigInteger.valueOf(-100L)");
        }
        try {
            Money money = new Money(RUB,null);
        } catch (MoneyException e) {
            System.out.println("Нелья создать объект: RUB, null");
        }
        try {
            Money money = new Money(null, null);
        } catch (MoneyException e) {
            System.out.println("Нелья создать объект: null, null");
        }
    }

    private static void testChangeCategory(Product product, int newCategory) {
        System.out.println(BEGIN_GREEN + "ТЕСТ СМЕНЫ КАТЕГОРИИ" + END_GREEN);
        Product newProduct = product.changeCategory(newCategory);
        Product productX = product.changePrice(new Money(product.getPrice().getCurrency(),
                BigInteger.valueOf(product.getPrice().getSum().longValue())));
        if (newProduct == product)
            System.out.println("Объекты идентичны: product == product1");
        else {
            System.out.println("newProduct = " + newProduct);
            System.out.println("Product = " + product);
        }

        if (productX.equals(product))
            System.out.println("Объекты идентичны: productX.equals(product1)");
        else
            System.out.println(productX);

        if (productX == product)
            System.out.println("Объекты идентичны: productX == product1");
        else
            System.out.println(productX);
    }

    private static void testChangePrice(Product product, long newSum) {
        System.out.println(BEGIN_GREEN + "ТЕСТ СМЕНЫ ЦЕНЫ" + END_GREEN);
        Product newProduct = product.changePrice(new Money(product.getPrice().getCurrency(),
                BigInteger.valueOf(newSum)));
        Product productX = product.changePrice(new Money(product.getPrice().getCurrency(),
                BigInteger.valueOf(product.getPrice().getSum().longValue())));
        if (newProduct == product)
            System.out.println("Объекты идентичны: product == product1");
        else {
            System.out.println("newProduct = " + newProduct);
            System.out.println("Product = " + product);
        }

        if (productX.equals(product))
            System.out.println("Объекты идентичны: productX.equals(product1)");
        else
            System.out.println(productX);

        if (productX == product)
            System.out.println("Объекты идентичны: productX == product1");
        else
            System.out.println(productX);
    }

    private static void testFindById(ProductCatalog catalog) {
        System.out.println(BEGIN_GREEN + "ТЕСТ ПОИСКА ПО ИД" + END_GREEN);
        try {
            Optional<Product> findBy2 = catalog.findById("2");
            Optional<Product> findBy20 = catalog.findById("20");
            System.out.println(findBy2.orElseThrow(() -> new NotFoundProduct("Не найден товар по id 2")));
            System.out.println(findBy20.orElseThrow(() -> new NotFoundProduct("Не найден товар по id 20")));
        } catch (NotFoundProduct e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при поиске товара по ИД");
        }
    }

    private static void testImmutableListInCatalog(List<Product> products) {
        System.out.println(BEGIN_GREEN + "ТЕСТ УДАЛЕНИЕ ИЗ КАТАЛОГА" + END_GREEN);
        try {
            Product removed = products.removeFirst();
            System.out.println("Removed = " + removed);
        } catch (Exception e) {
            System.out.println("Удаление из каталога невозможно");
        }
    }

    private static void testFindByCategory(ProductCatalog catalog) {
        System.out.println(BEGIN_GREEN + "ТЕСТ ПОИСКА ПО КАТЕГОРИИ" + END_GREEN);
        try {
            List<Product> findBy1 = catalog.findByCategory(1);
            List<Product> findBy6 = catalog.findByCategory(6);
            System.out.println(BEGIN_BLUE + "лист по категории 1" + END_BLUE);
            System.out.println(findBy1); // ид 1 и 4
            System.out.println(BEGIN_BLUE + "лист по категории 6" + END_BLUE);
            System.out.println(findBy6); // пусто
        } catch (Exception e) {
            System.out.println("Ошибка при поиске товара по категории");
        }
    }

    private static void testFindByPriceRange(ProductCatalog catalog) {
        System.out.println(BEGIN_GREEN + "ТЕСТ ПОИСКА ПО ЦЕНЕ РЕЙНЖДА" + END_GREEN);

        Money min = new Money(RUB, BigInteger.valueOf(100L));
        Money max400 = new Money(RUB, BigInteger.valueOf(400L));
        Money max1000 = new Money(RUB, BigInteger.valueOf(1000L));

        List<Product> products100_400 = catalog.findByPriceRange(min, max400);
        List<Product> products100_1000 = catalog.findByPriceRange(min, max1000);

        System.out.println(BEGIN_BLUE + "лист по цене 100-400" + END_BLUE);
        System.out.println(products100_400); // ид 1
        System.out.println(BEGIN_BLUE + "лист по цене 100-1500" + END_BLUE);
        System.out.println(products100_1000); // ид 1, 3
    }

    private static void testFindByPrice(ProductCatalog catalog) {
        System.out.println(BEGIN_GREEN + "ТЕСТ ПОИСКА ПО ЦЕНЕ" + END_GREEN);
        Money rub100 = new Money(RUB, BigInteger.valueOf(100L));
        Money usd100 = new Money(USD, BigInteger.valueOf(100L));

        List<Product> products100rub = catalog.findByPrice(rub100);
        List<Product> products100usd = catalog.findByPrice(usd100);

        System.out.println(BEGIN_BLUE + "лист по цене 100rub" + END_BLUE);
        System.out.println(products100rub); // ид 1
        System.out.println(BEGIN_BLUE + "лист по цене 100usd" + END_BLUE);
        System.out.println(products100usd); // ид 4

    }

}