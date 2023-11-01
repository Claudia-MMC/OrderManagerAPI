package com.exercise.ordermanager.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class OrderDTO {
    private Long id;
    private Long itemId;
    private int quantity;
    private LocalDateTime creationDate;
    private UserDTO user;
}
