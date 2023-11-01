package com.exercise.ordermanager.dto;

import com.exercise.ordermanager.entity.StockMovement;
import lombok.Data;

import java.util.List;

@Data
public class OrderCompletionStatusDTO {
    private Long orderId;
    private boolean completed;
    private List<StockMovement> stockMovements;
}
