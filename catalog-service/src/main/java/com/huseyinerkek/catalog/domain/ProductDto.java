package com.huseyinerkek.catalog.domain;

import java.math.BigDecimal;

public record ProductDto(String code, String name, String description, String imageUrl, BigDecimal price) {}
