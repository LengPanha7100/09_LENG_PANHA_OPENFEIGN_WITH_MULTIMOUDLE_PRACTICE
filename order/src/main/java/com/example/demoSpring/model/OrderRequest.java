package com.example.demoSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private List<Long> productId;
    private LocalDateTime dateTime;

    public Order toEntity(){
        return new Order(null,this.customerId,this.productId,this.dateTime);
    }
    public Order toEntity(Long id){
        return new Order(id,this.customerId,this.productId,this.dateTime);
    }
}
