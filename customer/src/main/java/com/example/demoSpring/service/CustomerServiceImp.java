package com.example.demoSpring.service;

import com.example.demoSpring.model.Customer;
import com.example.demoSpring.model.CustomerRequest;
import com.example.demoSpring.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService{
    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found")
        );
        return customer;
    }

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity());
    }

    @Override
    public Customer updateCustomer(CustomerRequest customerRequest, Long id) {
        return customerRepository.save(customerRequest.toEntity(id));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
