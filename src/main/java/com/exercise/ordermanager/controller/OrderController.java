package com.exercise.ordermanager.controller;

import com.exercise.ordermanager.dto.OrderDTO;
import com.exercise.ordermanager.service.OrderService;
import com.exercise.ordermanager.service.StockMovementService;
import com.exercise.ordermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable(name = "id") Long id){
        OrderDTO orderDTO = orderService.findById(id);
        if(Objects.nonNull(orderDTO)) {
            return ResponseEntity.ok().body(orderDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        return ResponseEntity.ok().body(orderService.findAll());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> register(@RequestBody OrderDTO orderDTO) {
            OrderDTO createdOrder = orderService.register(orderDTO);
            return ResponseEntity.ok().body(createdOrder);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable(name = "id") Long id, @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.update(id, orderDTO);
        if(Objects.nonNull(createdOrder)){
            return ResponseEntity.ok().body(createdOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.delete(id));
    }
}
