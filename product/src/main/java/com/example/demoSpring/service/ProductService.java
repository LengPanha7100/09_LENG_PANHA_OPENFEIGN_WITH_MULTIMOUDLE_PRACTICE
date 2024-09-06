package com.example.demoSpring.service;

import com.example.demoSpring.model.Product;
import com.example.demoSpring.model.ProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product getProductById(Long id);

    Product createProduct(ProductRequest productRequest);

    Product updateProduct(ProductRequest productRequest, Long id);

    void deleteProduct(Long id);
}
