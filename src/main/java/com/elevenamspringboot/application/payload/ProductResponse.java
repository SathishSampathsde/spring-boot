package com.elevenamspringboot.application.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int pageNo;
    private int pageSize;
    private long totalProducts;
    private int totalPages;
    private boolean last;
    private List<ProductDto> products;
}
