package com.huseyinerkek.catalog.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    public ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PagedResult<ProductDto> getProducts(int pageNo) {
        int page = pageNo <= 0 ? 1 : pageNo - 1;

        Pageable pageable = PageRequest.of(
                page, applicationProperties.pageSize(), Sort.by("name").ascending());

        Page<ProductDto> productDtoPage = productRepository.findAll(pageable).map(ProductMapper::toProductDto);

        return new PagedResult<>(
                productDtoPage.getContent(),
                productDtoPage.getTotalElements(),
                productDtoPage.getNumber() + 1,
                productDtoPage.getTotalPages(),
                productDtoPage.isFirst(),
                productDtoPage.isLast(),
                productDtoPage.hasNext(),
                productDtoPage.hasPrevious());
    }

    public Optional<ProductDto> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProductDto);
    }
}
