package org.my.edy.service;

import org.my.edy.model.Money;
import org.my.edy.model.Product;

import java.util.List;
import java.util.Optional;

public interface FindInCatalog {
    Optional<Product> findById (String id);
    List<Product> findByCategory(int category);
    List<Product> findByPrice(Money price);
    List<Product> findByPriceRange(Money min, Money max);
}
