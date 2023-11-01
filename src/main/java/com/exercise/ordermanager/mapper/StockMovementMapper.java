package com.exercise.ordermanager.mapper;

import com.exercise.ordermanager.dto.StockMovementDTO;
import com.exercise.ordermanager.entity.StockMovement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMovementMapper {

    public StockMovement toStockMovement(StockMovementDTO stockMovementDTO) {
        return StockMovement.builder()
                .item(stockMovementDTO.getItem())
                .quantity(stockMovementDTO.getQuantity())
                .creationDate(stockMovementDTO.getCreationDate())
                .build();
    }

    public StockMovementDTO toStockMovementDTO(StockMovement stockMovement) {
        return new StockMovementDTO(stockMovement);
    }

    public List<StockMovementDTO> toStockMovementsDTO(List<StockMovement> stockMovements) {
        return stockMovements.stream().map(StockMovementDTO::new).collect(Collectors.toList());
    }

    public void update(StockMovement stockMovement, StockMovementDTO stockMovementDTO){
        stockMovement.setId(stockMovementDTO.getId());
        stockMovement.setItem(stockMovementDTO.getItem());
        stockMovement.setQuantity(stockMovementDTO.getQuantity());
        stockMovement.setCreationDate(stockMovementDTO.getCreationDate());
    }
}
