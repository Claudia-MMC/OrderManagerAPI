package com.exercise.ordermanager.dto;

import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.entity.Order;
import com.exercise.ordermanager.entity.StockMovement;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StockMovementDTO {
    private Long id;
    private Item item;
    private int quantity;
    private int attributedQuantity;
    private Order order;
    private LocalDateTime creationDate;

    public StockMovementDTO(StockMovement stockMovement) {
        this.id = stockMovement.getId();
        this.item = stockMovement.getItem();
        this.quantity = stockMovement.getQuantity();
        this.attributedQuantity = stockMovement.getAttributedQuantity();
        this.order = stockMovement.getOrder();
        this.creationDate = stockMovement.getCreationDate();
    }
}
