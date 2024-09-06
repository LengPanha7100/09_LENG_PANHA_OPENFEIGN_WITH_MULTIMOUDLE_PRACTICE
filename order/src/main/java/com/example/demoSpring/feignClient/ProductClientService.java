package com.example.demoSpring.feignClient;

import com.example.demoSpring.model.APIResponse;
import com.example.demoSpring.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service",url = "http://localhost:8082/api/v1/product")
public interface ProductClientService {
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<Product>> getProductById(@PathVariable Long id);
}
