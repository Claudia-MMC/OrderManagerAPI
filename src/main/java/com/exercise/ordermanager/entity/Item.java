package com.exercise.ordermanager.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Builder
    public Item(Long id, String name, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }
}
