package com.buensabor.backend.Orders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buensabor.backend.Orders.Entity.Dto.OrderDto;
import com.buensabor.backend.Orders.EnumOrder.OrderStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    // ------------------- PUBLIC -------------------

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getOrders() {
        try {
            List<OrderDto> orders = orderService.getAllOrders();

            return ResponseEntity.ok().body(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@RequestParam Long id) {
        try {

            OrderDto order = orderService.getOrderById(id);

            return ResponseEntity.ok().body(order);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getOrderByStatus(@RequestParam(required = false) OrderStatus status){
        try {

            List<OrderDto> orders = orderService.getOrdersByStatus(status);

            return ResponseEntity.ok().body(orders);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        try {
            OrderDto order = orderService.createOrder(orderDto);
            return ResponseEntity.ok().body(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body("Nice");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {

            orderService.deleteOrder(id);

            return ResponseEntity.ok().body("Order deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ------------------- ADMIN -------------------

}
