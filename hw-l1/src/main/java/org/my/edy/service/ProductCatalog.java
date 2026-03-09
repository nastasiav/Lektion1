package org.my.edy.service;

import org.my.edy.model.Money;
import org.my.edy.model.Product;

import java.util.*;

public class ProductCatalog implements FindInCatalog {
    private final TreeMap<Product, Product> productCatalog;

    public ProductCatalog(ProductComparator comparator) {
        productCatalog = new TreeMap<>(comparator);
    }

    public TreeMap<Product, Product> getProductCatalog() {
        return productCatalog;
    }

    /**
     * Список всех товаров
     * @return список всех товаров в каталоге
     */
    public List<Product> getAllProducts() {
        return List.copyOf(productCatalog.values());
    }

    /**
     * Добавить продукт
     * @param product добавление
     */
    public void addProduct(Product product) {
        productCatalog.put(product, product);
    }

    /**
     * Найти товар по ID
     * @param id Ид товара
     * @return product найденный продукт
     */
    public Optional<Product> findById (String id) {
        return Optional.ofNullable(getById(id));
    }

    /**
     * Поист товара про категории
     * @param category номер категории
     * @return список продуктов
     */
    public List<Product> findByCategory(int category) {
        return productCatalog.values().stream()
                .filter(el -> el.getCategory() == category)
                .toList();
    }

    /**
     * Поист товара про категории
     * @param price цена
     * @return список продуктов
     */
    public List<Product> findByPrice(Money price) {
        return productCatalog.values().stream()
                .filter(el -> el.getPrice().getCurrency().equals(price.getCurrency())
                && el.getPrice().getSum().equals(price.getSum()))
                .toList();
    }

    /**
     * Найти по цене мин-макс
     * @param min минимальное значение
     * @param max максимальное значение
     * @return список товаров
     */
    public List<Product> findByPriceRange(Money min, Money max) {
        if (!min.getCurrency().equals(max.getCurrency()))
            throw new IllegalArgumentException("Валюта min и max не совпадает");
        return productCatalog.values().stream()
                .filter(p -> p.getPrice().getCurrency().equals(min.getCurrency()))
                .filter(p -> p.getPrice().getSum().compareTo(min.getSum()) >= 0
                        && p.getPrice().getSum().compareTo(max.getSum()) <= 0)
                .toList();
    }

    @Override
    public String toString() {
        return "ProductCatalog{" +
                "productCatalog=" + productCatalog +
                '}';
    }

    private Product getById(String id) {
        for (Product p : productCatalog.keySet()) {
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }
}
