package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.ItemDTO;
import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.mapper.ItemMapper;
import com.exercise.ordermanager.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    @Override
    public ItemDTO findById(Long id) {
        return ItemMapper.itemToItemDto(returnItem(id));
    }

    public ItemDTO findByName(String name) {
        return ItemMapper.itemToItemDto(itemRepository.findByName(name));
    }

    @Override
    public List<ItemDTO> findAll() {
        return ItemMapper.itemListToItemDtoList(itemRepository.findAll());
    }

    @Override
    public ItemDTO register(ItemDTO itemDTO) {
        try {
            Item item = ItemMapper.itemDtoToItem(itemDTO);
            logger.info("Item created. ID: " + item.getId());
            return ItemMapper.itemToItemDto(itemRepository.save(item));
        } catch (Exception e) {
            logger.error("Error creating item: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ItemDTO update(Long id, ItemDTO itemDTO) {
        try {
            Item item = returnItem(id);
            if(Objects.nonNull(item)) {
                ItemMapper.updateItem(item, itemDTO);
                logger.info("Item updated. ID: " + item.getId());
                return ItemMapper.itemToItemDto(itemRepository.save(item));
            } else {
                logger.error("Item not found. ID: " + id);
                throw new ObjectNotFoundException("Item not found: ID=" + id, item);
            }
        } catch (Exception e) {
            logger.error("Error updating item: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String delete(Long id) {
        try {
            if(itemRepository.existsById(id)) {
                itemRepository.deleteById(id);
                logger.info("Item deleted. ID: " + id);
                return "Item id:" +id+ "successfully deleted";
            } else {
                logger.error("Item not found. ID: " + id);
                return "Item id:" +id+ "not found";
            }
        } catch (Exception e) {
            logger.error("Error deleting item: " + e.getMessage(), e);
            throw e;
        }
    }

    private Item returnItem(Long id) {
        return itemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Item not found"));
    }
}
