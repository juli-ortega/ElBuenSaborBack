package com.buensabor.backend.Orders.Mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.buensabor.backend.Orders.Entity.Order;
import com.buensabor.backend.Orders.Entity.Dto.OrderDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    // Mapeo de entidades
    OrderDto toOrderDTO(Order order);

    Order toOrder(OrderDto orderDto);

    // Mapeo de listas
    List<OrderDto> toOrderDTOList(List<Order> orders);

    List<Order> toOrderList(List<OrderDto> ordersDto);
    
}
