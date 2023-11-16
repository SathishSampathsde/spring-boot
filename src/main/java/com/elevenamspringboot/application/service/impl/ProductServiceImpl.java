package com.elevenamspringboot.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.elevenamspringboot.application.entity.Product;
import com.elevenamspringboot.application.exception.ResourceNotFoundException;
import com.elevenamspringboot.application.payload.ProductDto;
import com.elevenamspringboot.application.payload.ProductResponse;
import com.elevenamspringboot.application.repository.ProductRepository;
import com.elevenamspringboot.application.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        // convert dto to entity
        // Product product = new Product();
        // product.setTitle(productDto.getTitle());
        // product.setDescription(productDto.getDescription());
        // product.setPrice(productDto.getPrice());
        // product.setImage(productDto.getImage());

        Product product = mapToEntity(productDto);

        // insert to database
        Product newProduct = productRepository.save(product);

        // convert entity to dto

        // ProductDto createProductResponse = new ProductDto();

        // createProductResponse.setId(newProduct.getId());
        // createProductResponse.setTitle(newProduct.getTitle());
        // createProductResponse.setDescription(newProduct.getDescription());
        // createProductResponse.setPrice(newProduct.getPrice());
        // createProductResponse.setImage(newProduct.getImage());
        // createProductResponse.setModifiedOn(newProduct.getModifiedOn());
        // createProductResponse.setCreatedOn(newProduct.getCreatedOn());

        ProductDto createProductResponse = mapToDto(newProduct);
        return createProductResponse;
    }

    @Override
    public ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
                
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Product> productsPage = productRepository.findAll(pageable);

        // get content for page object
        List<Product> listOfProducts = productsPage.getContent();

        List<ProductDto> products = listOfProducts.stream().map(product -> mapToDto(product))
                .collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setPageNo(productsPage.getNumber() + 1);
        productResponse.setPageSize(productsPage.getSize());
        productResponse.setTotalPages(productsPage.getTotalPages());
        productResponse.setTotalProducts(productsPage.getTotalElements());
        productResponse.setLast(productsPage.isLast());
        productResponse.setProducts(products);
        return productResponse;
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());

        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }

    // map to entity
    private Product mapToEntity(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        return product;
    }

    // map to Dto
    private ProductDto mapToDto(Product product) {
        ProductDto productDto = mapper.map(product, ProductDto.class);
        return productDto;
    }

}
