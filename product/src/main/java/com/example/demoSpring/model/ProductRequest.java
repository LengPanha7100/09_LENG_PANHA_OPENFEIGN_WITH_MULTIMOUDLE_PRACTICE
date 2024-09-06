package com.example.demoSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private Double price;

    public Product toEntity(){
        return new Product(null,this.name,this.price);
    }
    public Product toEntity(Long id ){
        return new Product(id,this.name,this.price);
    }
}
