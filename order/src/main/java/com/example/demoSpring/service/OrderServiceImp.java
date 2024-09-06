package com.example.demoSpring.service;

import com.example.demoSpring.feignClient.CustomerClientService;
import com.example.demoSpring.feignClient.ProductClientService;
import com.example.demoSpring.model.*;
import com.example.demoSpring.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService{
    private final OrderRepository orderRepository;
    private final CustomerClientService customerClientService;
    private final ProductClientService productClientService;

    public OrderServiceImp(OrderRepository orderRepository, CustomerClientService customerClientService, ProductClientService productClientService) {
        this.orderRepository = orderRepository;
        this.customerClientService = customerClientService;
        this.productClientService = productClientService;
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order1 : orders){
            ResponseEntity<APIResponse<Customer>> response = customerClientService.getCustomerById(order1.getCustomerId());
            List<Product> products = new ArrayList<>();
            for(Long productId : order1.getProductId()){
                ResponseEntity<APIResponse<Product>> responseEntity = productClientService.getProductById(productId);
                products.add(responseEntity.getBody().getPayload());
            }
            orderResponseList.add(order1.toResponse(response.getBody().getPayload(),products));
        }
        return orderResponseList;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order orders = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        assert orders != null;
        ResponseEntity<APIResponse<Customer>> response = customerClientService.getCustomerById(orders.getCustomerId());
        List<Product> products = new ArrayList<>();
        for (Long productId : orders.getProductId()){
            ResponseEntity<APIResponse<Product>> responseEntity = productClientService.getProductById(productId);
            products.add(responseEntity.getBody().getPayload());
        }
//        try {
//            ResponseEntity<APIResponse<Customer>> response = customerClientService.getCustomerById(orders.getCustomerId());
//            Customer customer = response.getBody().getPayload();
//            List<Product> products = new ArrayList<>();
//            for (Long productId : orders.getProductId()){
//                ResponseEntity<APIResponse<Product>> responseEntity = productClientService.getProductById(productId);
//                products.add(responseEntity.getBody().getPayload());
//            }
//
//        }catch(Exception e){
//            throw new RuntimeException("Not found");
//        }
        return orders.toResponse(response.getBody().getPayload(),products);
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        ResponseEntity<APIResponse<Customer>> response = customerClientService.getCustomerById(orderRequest.getCustomerId());
        List<Product> products = new ArrayList<>();
        for(Long productId : orderRequest.getProductId()){
            ResponseEntity<APIResponse<Product>> responseEntity = productClientService.getProductById(productId);
            products.add(responseEntity.getBody().getPayload());
        }
        return orderRepository.save(orderRequest.toEntity()).toResponse(response.getBody().getPayload(),products);
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        ResponseEntity<APIResponse<Customer>> response = customerClientService.getCustomerById(orderRequest.getCustomerId());
        List<Product> products = new ArrayList<>();
        for(Long productId : orderRequest.getProductId()){
            ResponseEntity<APIResponse<Product>> responseEntity = productClientService.getProductById(productId);
            products.add(responseEntity.getBody().getPayload());
        }
        return orderRepository.save(orderRequest.toEntity(id)).toResponse(response.getBody().getPayload(),products);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        orderRepository.deleteById(id);
    }
}
