package com.example.demoSpring.feignClient;

import com.example.demoSpring.model.APIResponse;
import com.example.demoSpring.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service",url = "http://localhost:8081/api/v1/customer")
public interface CustomerClientService {
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<Customer>> getCustomerById(@PathVariable Long id);
}
