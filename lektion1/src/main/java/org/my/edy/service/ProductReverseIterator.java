package org.my.edy.service;

import org.my.edy.exception.NotFoundProduct;
import org.my.edy.model.Product;

import java.util.*;

public class ProductReverseIterator implements Iterator<Product> {
    private final NavigableSet<Product> productList;
    private int size;

    public ProductReverseIterator(TreeMap<Product, Product> catalog) {
        productList = catalog.descendingKeySet();
        size = productList != null ? productList.size() : 0;
    }

    @Override
    public boolean hasNext() {
        return size > 0;
    }

    @Override
    public Product next() {
        if (!hasNext())
            throw new NotFoundProduct("Больше нет элементов");
        size--;
        return productList.pollFirst();
    }
}