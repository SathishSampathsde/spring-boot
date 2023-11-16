package com.elevenamspringboot.application.service;

import java.util.List;

import com.elevenamspringboot.application.payload.ProductDto;
import com.elevenamspringboot.application.payload.ProductResponse;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy,String sortDir);

    ProductDto getProductById(long id);

    ProductDto updateProduct(long id, ProductDto productDto);

    void deleteProduct(long id);
}
