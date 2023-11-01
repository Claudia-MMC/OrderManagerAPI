package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.OrderDTO;
import com.exercise.ordermanager.entity.Order;
import com.exercise.ordermanager.entity.StockMovement;

import java.util.List;

public interface OrderService {

    OrderDTO findById(Long id);

    List<OrderDTO> findAll();

    OrderDTO register(OrderDTO orderDTO);

    OrderDTO update(Long id, OrderDTO orderDTO);

    String delete(Long id);

    List<Order> findIncompleteOrders();

    List<StockMovement> getStockMovementsForOrder(Long orderId);
}
