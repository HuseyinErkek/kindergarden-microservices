package com.huseyinerkek.catalog.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.huseyinerkek.catalog.AbstractIT;
import com.huseyinerkek.catalog.domain.ProductDto;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true));
    }

    @Test
    void shouldReturnProductWhenCodeExists() {
        String productCode = "P101";

        ProductDto productDto = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/" + productCode)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductDto.class);

        assertThat(productDto.code()).isEqualTo("P101");
        assertThat(productDto.name()).isEqualTo("Clean Code");
        assertThat(productDto.description())
                .isEqualTo("A Handbook of Agile Software Craftsmanship by Robert C. Martin");
        assertThat((productDto.imageUrl())).isEqualTo("https://via.placeholder.com/150");
        assertThat(productDto.price()).isEqualTo(new BigDecimal("120.00"));
    }

    @Test
    @Disabled("RestAssured yapılandırma sorunu nedeniyle geçici olarak kapatıldı. Yeni bir issue ile düzeltilecek.")
    void shouldReturnNotFoundWhenProductCodeNotExists() {
        String code = "invalid_product_code";

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Ürün bulunamadı."))
                .body("detail", is("Bu ürün koduyla " + code + " ürün bulunamadı."));
    }
}
