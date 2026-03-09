package org.my.edy.service;

import org.my.edy.model.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        int priceCmp = p2.getPrice().getSum().compareTo(p1.getPrice().getSum());
        if (priceCmp != 0) return priceCmp;

        return p1.getId().compareTo(p2.getId());
    }
}
