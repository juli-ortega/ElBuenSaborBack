package com.buensabor.backend.Orders;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buensabor.backend.Orders.Entity.Order;
import com.buensabor.backend.Orders.Entity.Dto.OrderDto;
import com.buensabor.backend.Orders.EnumOrder.OrderStatus;
import com.buensabor.backend.Orders.Mapper.OrderMapper;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    /**
     * Get all orders by status
     * @param orderStatus("PENDING","CANCELED","COMPLETED")
     * @return
     */
    public List<OrderDto> getOrdersByStatus(OrderStatus status) {
        try {

            List<Order> orders = orderRepository.findByStatus(status);

            return orderMapper.toOrderDTOList(orders); 

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all orders
     * @return
     */
    public List<OrderDto> getAllOrders() {
        try {

            List<Order> orders = orderRepository.findAll();

            return orderMapper.toOrderDTOList(orders);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get an order by id
     * @param id
     * @return
     */
    public OrderDto getOrderById(Long id) {
        try {

            Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

            return orderMapper.toOrderDTO(order);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Crete a new order
     * @param orderDto
     * @return
     */
    public OrderDto createOrder(OrderDto orderDto) {
        try {

            Order order = orderMapper.toOrder(orderDto);
            Order orderCreated = orderRepository.save(order);

            OrderDto orderDtoCreated = orderMapper.toOrderDTO(orderCreated);

            return orderDtoCreated;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update an order
     * @param orderDto
     * @return
     */
    public Order updateOrder(OrderDto orderDto) {
        try {

            Order order = orderMapper.toOrder(orderDto);

            return orderRepository.save(order);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete an order
     * @param id
     */
    public void deleteOrder(Long id) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
            
            order.setDeletedAt(LocalDateTime.now());// Establecer la fecha de eliminación lógica
            
            orderRepository.save(order); // Guardar la orden con la fecha de eliminación
        } catch (Exception e) {
            throw new RuntimeException("Error deleting order", e);
        }
    }
    }

