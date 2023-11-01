package com.exercise.ordermanager.mapper;

import com.exercise.ordermanager.dto.ItemDTO;
import com.exercise.ordermanager.entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public static Item itemDtoToItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        return item;
    }

    public static ItemDTO itemToItemDto(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        return itemDTO;
    }

    public static List<Item> itemDtoListToItemList(List<ItemDTO> itemDTOList) {
        return itemDTOList.stream()
                .map(ItemMapper::itemDtoToItem)
                .collect(Collectors.toList());
    }

    public static List<ItemDTO> itemListToItemDtoList(List<Item> itemList) {
        return itemList.stream()
                .map(ItemMapper::itemToItemDto)
                .collect(Collectors.toList());
    }

    public static void updateItem(Item existingItem, ItemDTO itemDTO) {
        existingItem.setName(itemDTO.getName());
    }
}
