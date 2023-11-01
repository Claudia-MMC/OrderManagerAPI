package com.exercise.ordermanager.controller;

import com.exercise.ordermanager.dto.StockMovementDTO;
import com.exercise.ordermanager.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/stock-movement")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<StockMovementDTO> findById(@PathVariable(name = "id") Long id) {
        StockMovementDTO createdStockMovement = stockMovementService.findById(id);
        if (Objects.nonNull(createdStockMovement)) {
            return ResponseEntity.ok().body(createdStockMovement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StockMovementDTO>> findAll() {
        return ResponseEntity.ok().body(stockMovementService.findAll());
    }

    @PostMapping
    public ResponseEntity<StockMovementDTO> register(StockMovementDTO stockMovementDTO, UriComponentsBuilder uriBuilder) {
        StockMovementDTO createdStockMovementDTO = stockMovementService.register(stockMovementDTO);
        URI uri = uriBuilder.path("/stock-movement/{id}").buildAndExpand(createdStockMovementDTO.getItem()).toUri();
        return ResponseEntity.created(uri).body(createdStockMovementDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StockMovementDTO> update(@PathVariable(name = "id") Long id, @RequestBody StockMovementDTO stockMovementDTO) {
        StockMovementDTO createdStockMovement = stockMovementService.update(id, stockMovementDTO);
        if (Objects.nonNull(createdStockMovement)) {
            return ResponseEntity.ok().body(createdStockMovement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(stockMovementService.delete(id));
    }
}
