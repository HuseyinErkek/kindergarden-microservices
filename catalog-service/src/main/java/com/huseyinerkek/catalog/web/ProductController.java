package com.huseyinerkek.catalog.web;

import com.huseyinerkek.catalog.domain.PagedResult;
import com.huseyinerkek.catalog.domain.ProductDto;
import com.huseyinerkek.catalog.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductDto> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }
}
