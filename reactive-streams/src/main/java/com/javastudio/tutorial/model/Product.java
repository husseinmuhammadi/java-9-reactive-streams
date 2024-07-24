package com.javastudio.tutorial.model;

public class Product {
    private final Long id;
    private final String name;

    private Product(ProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Long id;
        private String name;

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Product build() {
            Product product = new Product(this);
            validateProductObject(product);
            return product;
        }

        private void validateProductObject(Product product) {

        }
    }
}
