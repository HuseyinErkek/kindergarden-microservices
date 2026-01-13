package com.huseyinerkek.catalog.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Bu ürün koduyla " + code + " ürün bulunamadı.");
    }
}
