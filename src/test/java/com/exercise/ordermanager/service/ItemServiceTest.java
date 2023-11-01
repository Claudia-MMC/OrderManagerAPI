package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.ItemDTO;
import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;
    @Mock
    private ItemRepository itemRepository;


    @Test
    public void testCreateItem() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Test Item");

        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");

        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ItemDTO createdItemDTO = itemService.register(itemDTO);

        assertNotNull(createdItemDTO.getId());
        assertEquals("Test Item", createdItemDTO.getName());
    }

    @Test
    public void testUpdateItem() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setName("Updated Item");

        Item item = new Item();
        item.setId(1L);
        item.setName("Updated Item");

        when(itemRepository.findById(any(Long.class))).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ItemDTO updatedItemDTO = itemService.update(item.getId(), itemDTO);

        assertNotNull(updatedItemDTO);
        assertEquals(itemDTO.getId(), updatedItemDTO.getId());
        assertEquals("Updated Item", updatedItemDTO.getName());
    }

    @Test
    public void testFindAllItems() {
        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("Item 1");

        Item item2 = new Item();
        item2.setId(2L);
        item2.setName("Item 2");

        when(itemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<ItemDTO> items = itemService.findAll();

        assertNotNull(items);
        assertEquals(2, items.size());
        assertEquals(1L, items.get(0).getId());
        assertEquals("Item 1", items.get(0).getName());
    }

}
