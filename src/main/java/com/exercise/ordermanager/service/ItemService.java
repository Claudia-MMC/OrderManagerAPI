package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemDTO findById(Long id);

    List<ItemDTO> findAll();

    ItemDTO findByName(String name);

    ItemDTO register(ItemDTO itemDTO);

    ItemDTO update(Long id, ItemDTO itemDTO);

    String delete(Long id);
}
