package com.huseyinerkek.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "ürün kodu gereklidir.")
    private String code;

    @Column(nullable = false)
    @NotBlank(message = " Ürüm ismi gereklidir.")
    private String name;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    @NotNull(message = "Ürün fiyatı gereklidir.")
    private BigDecimal price;
}

