package com.example.demoSpring.controller;

import com.example.demoSpring.model.APIResponse;
import com.example.demoSpring.model.Customer;
import com.example.demoSpring.model.CustomerRequest;
import com.example.demoSpring.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public ResponseEntity<?> getAllCustomer(){
        List<Customer> customer = customerService.getAllCustomer();
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully get all customer")
                .payload(customer)
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully get by id customer")
                .payload(customer)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<APIResponse<Customer>> createCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.createCustomer(customerRequest);
        APIResponse<Customer> apiResponse = APIResponse.<Customer>builder()
                .message("Successfully saved customer")
                .payload(customer)
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerRequest,@PathVariable Long id ){
        Customer customer = customerService.updateCustomer(customerRequest,id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully updated customer")
                .payload(customer)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer (@PathVariable Long id){
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully deleted customer")
                .payload(null)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(apiResponse);

    }

}
