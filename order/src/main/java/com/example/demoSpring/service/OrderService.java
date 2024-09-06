package com.example.demoSpring.service;

import com.example.demoSpring.model.Order;
import com.example.demoSpring.model.OrderRequest;
import com.example.demoSpring.model.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrder();

    OrderResponse getOrderById(Long id);

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse updateOrder(OrderRequest orderRequest, Long id);

    void deleteOrder(Long id);
}
