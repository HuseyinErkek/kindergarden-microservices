package com.huseyinerkek.catalog.domain;

class ProductMapper {

    static ProductDto toProductDto(ProductEntity productEntity) {
        return new ProductDto(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice());
    }
}
