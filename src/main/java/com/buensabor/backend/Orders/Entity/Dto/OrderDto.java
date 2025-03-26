package com.buensabor.backend.Orders.Entity.Dto;

import java.time.LocalDateTime;

import com.buensabor.backend.Orders.EnumOrder.OrderStatus;
import com.buensabor.backend.Orders.EnumOrder.OrderTypeOfShipping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private int number;
    private OrderTypeOfShipping typeOfShipping;
    private LocalDateTime date;
    private double total;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}