package com.buensabor.backend.Orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buensabor.backend.Orders.Entity.Order;
import com.buensabor.backend.Orders.EnumOrder.OrderStatus;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByStatus(OrderStatus status) {
        try {
            return orderRepository.findByStatus(status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Order createOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

