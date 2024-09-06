package com.example.demoSpring.controller;

import com.example.demoSpring.ProductApplication;
import com.example.demoSpring.model.APIResponse;
import com.example.demoSpring.model.Product;
import com.example.demoSpring.model.ProductRequest;
import com.example.demoSpring.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        List<Product> product = productService.getAllProduct();
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully get all product")
                .payload(product)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully get by id product")
                .payload(product)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        Product product = productService.createProduct(productRequest);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully saved product")
                .payload(product)
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable Long id ){
        Product product = productService.updateProduct(productRequest,id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully updated product")
                .payload(product)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id ){
        productService.deleteProduct(id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully deleted product")
                .payload(null)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }



}
