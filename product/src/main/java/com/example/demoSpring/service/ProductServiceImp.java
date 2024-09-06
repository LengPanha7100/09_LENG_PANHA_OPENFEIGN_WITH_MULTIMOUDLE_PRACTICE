package com.example.demoSpring.service;

import com.example.demoSpring.model.Product;
import com.example.demoSpring.model.ProductRequest;
import com.example.demoSpring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements  ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found")
        );
        return product;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity());
    }

    @Override
    public Product updateProduct(ProductRequest productRequest, Long id) {
        return productRepository.save(productRequest.toEntity(id));
    }

    @Override
    public void deleteProduct(Long id) {
       productRepository.deleteById(id);
    }
}
