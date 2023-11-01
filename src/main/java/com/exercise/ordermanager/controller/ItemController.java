package com.exercise.ordermanager.controller;

import com.exercise.ordermanager.dto.ItemDTO;
import com.exercise.ordermanager.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable(name = "id") Long id) {
        ItemDTO createdItemDTO = itemService.findById(id);
        if (Objects.nonNull(createdItemDTO)) {
            return ResponseEntity.ok().body(itemService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll() {
        return ResponseEntity.ok().body(itemService.findAll());
    }

    @PostMapping
    public ResponseEntity<ItemDTO> register(@RequestBody ItemDTO itemDTO, UriComponentsBuilder uriBuilder) {
        ItemDTO createdItemDTO = itemService.register(itemDTO);
        URI uri = uriBuilder.path("/item/{id}").buildAndExpand(createdItemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(createdItemDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable(name = "id") Long id, @RequestBody ItemDTO itemRequestDTO) {
        ItemDTO createdItemDTO = itemService.update(id, itemRequestDTO);
        if (Objects.nonNull(createdItemDTO)) {
            return ResponseEntity.ok().body(itemService.update(id, itemRequestDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(itemService.delete(id));
    }
}
