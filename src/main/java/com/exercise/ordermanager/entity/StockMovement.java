package com.exercise.ordermanager.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movement")
@Data
@NoArgsConstructor
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "attributed_quantity")
    private int attributedQuantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Builder
    public StockMovement(Long id, Item item, int quantity, Order order, LocalDateTime creationDate) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.order = order;
        this.creationDate = creationDate;
    }
}
