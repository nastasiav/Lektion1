package org.my.edy.model;

import org.my.edy.exception.ProductException;

import java.util.Objects;

public final class Product {
    private final String id;
    private final String name;
    private final Money price;
    private final Integer category;

    public Product(String id, String name, Money price, int category) {
        if (id == null || name == null || price == null || category == 0)
            throw new ProductException("Ни один параметр не может быть null");
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(Product product, Money newPrice) {
        this.id = product.id;
        this.name = product.name;
        this.price = newPrice;
        this.category = product.category;
    }

    public Product(Product product, int newCategory) {
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
        this.category = newCategory;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }

    public Product changePrice(Money price) {
        return new Product(this, price);
    }

    public Product changeCategory(int category) {
        return new Product(this, category);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return category == product.category
                && Objects.equals(id, product.id)
                && Objects.equals(name, product.name)
                && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
