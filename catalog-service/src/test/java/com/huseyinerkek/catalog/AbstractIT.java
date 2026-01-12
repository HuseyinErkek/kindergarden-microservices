package com.huseyinerkek.catalog;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

// 1. Rastgele port ile sunucuyu kaldır
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(ContainersConfig.class)
public abstract class AbstractIT {

    // 2. Spring'in seçtiği o rastgele portu buraya enjekte et
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        // 3. RestAssured'a "Bu portu kullan"
        RestAssured.port = port;
    }
}
