package com.example.demoSpring.Controller;

import com.example.demoSpring.model.APIResponse;
import com.example.demoSpring.model.Order;
import com.example.demoSpring.model.OrderRequest;
import com.example.demoSpring.model.OrderResponse;
import com.example.demoSpring.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public ResponseEntity<?> getAllOrder(){
        List<OrderResponse> order = orderService.getAllOrder();
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully get all order")
                .payload(order)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        OrderResponse order = orderService.getOrderById(id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully get by id order")
                .payload(order)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse order = orderService.createOrder(orderRequest);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully created order")
                .payload(order)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody OrderRequest orderRequest,@PathVariable Long id){
        OrderResponse order = orderService.updateOrder(orderRequest,id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully updated order")
                .payload(order)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        APIResponse<?> apiResponse = APIResponse.builder()
                .message("Successfully deleted order")
                .payload(null)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }



}
