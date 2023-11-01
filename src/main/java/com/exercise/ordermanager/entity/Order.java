package com.exercise.ordermanager.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<StockMovement> stockMovements;

    @Column(name = "completed")
    private boolean completed;

    @Builder
    public Order(Long id, Item item, int quantity, LocalDateTime creationDate, User user, boolean completed) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.creationDate = creationDate;
        this.user = user;
        this.completed = completed;
    }
}
