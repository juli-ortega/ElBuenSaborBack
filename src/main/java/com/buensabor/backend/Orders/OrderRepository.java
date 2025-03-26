package com.buensabor.backend.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buensabor.backend.Orders.Entity.Order;
import com.buensabor.backend.Orders.EnumOrder.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByStatus(OrderStatus status);
    
} 
    

