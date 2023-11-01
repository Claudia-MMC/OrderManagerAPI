package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.StockMovementDTO;
import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.entity.Order;
import com.exercise.ordermanager.entity.StockMovement;

import java.util.List;

public interface StockMovementService {

    StockMovementDTO findById(Long id);

    StockMovementDTO findByItem(Item item);

    List<StockMovement> findByOrder(Order order);

    List<StockMovementDTO> findAll();

    StockMovementDTO register(StockMovementDTO stockMovementDTO);

    StockMovementDTO update(Long id, StockMovementDTO stockMovementDTO);

    String delete(Long id);
}
