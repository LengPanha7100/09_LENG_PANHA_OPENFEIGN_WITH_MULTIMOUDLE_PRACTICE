package com.example.demoSpring.service;

import com.example.demoSpring.model.Customer;
import com.example.demoSpring.model.CustomerRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();

    Customer getCustomerById(Long id);

    Customer createCustomer(CustomerRequest customerRequest);

    Customer updateCustomer(CustomerRequest customerRequest, Long id);

    void deleteCustomer(Long id);
}
