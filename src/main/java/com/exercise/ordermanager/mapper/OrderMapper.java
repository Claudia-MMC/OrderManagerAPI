package com.exercise.ordermanager.mapper;

import com.exercise.ordermanager.dto.OrderDTO;
import com.exercise.ordermanager.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public static Order orderDtoToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setQuantity(orderDTO.getQuantity());
        order.setCreationDate(orderDTO.getCreationDate());
        order.setUser(UserMapper.userDtoToUser(orderDTO.getUser()));
        return order;
    }

    public static OrderDTO orderToOrderDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setItemId(order.getItem().getId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setCreationDate(order.getCreationDate());
        orderDTO.setUser(UserMapper.userToUserDto(order.getUser()));
        return orderDTO;
    }

    public static List<Order> orderDtoListToOrderList(List<OrderDTO> orderDTOList) {
        return orderDTOList.stream()
                .map(OrderMapper::orderDtoToOrder)
                .collect(Collectors.toList());
    }

    public static List<OrderDTO> orderListToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(OrderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    public static void update(Order existingOrder, OrderDTO orderDTO) {
        existingOrder.setCreationDate(orderDTO.getCreationDate());
        existingOrder.setQuantity(orderDTO.getQuantity());
        existingOrder.setUser(UserMapper.userDtoToUser(orderDTO.getUser()));
    }
}
