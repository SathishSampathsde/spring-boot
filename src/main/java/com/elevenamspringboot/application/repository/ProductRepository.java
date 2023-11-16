package com.elevenamspringboot.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elevenamspringboot.application.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
