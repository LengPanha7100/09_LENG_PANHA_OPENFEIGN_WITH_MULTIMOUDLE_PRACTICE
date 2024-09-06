package com.example.demoSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String email;

    public Customer toEntity(){
        return new Customer(null,this.name,this.email);
    }
    public Customer toEntity(Long id){
        return new Customer(id,this.name,this.email);
    }
}
