package com.exercise.ordermanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.exercise.ordermanager.dto.OrderDTO;
import com.exercise.ordermanager.dto.UserDTO;
import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.entity.Order;
import com.exercise.ordermanager.entity.StockMovement;
import com.exercise.ordermanager.entity.User;
import com.exercise.ordermanager.mapper.UserMapper;
import com.exercise.ordermanager.repository.ItemRepository;
import com.exercise.ordermanager.repository.OrderRepository;
import com.exercise.ordermanager.repository.StockMovementRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ItemRepository itemRepository;

    @Mock
    private StockMovementServiceImpl stockMovementService;

    private User user;
    private Item item;
    private OrderDTO orderDTO;
    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@email.com");

        item = new Item();
        item.setId(1L);
        item.setName("Test Item");
        item.setStockQuantity(10);

        orderDTO = new OrderDTO();
        orderDTO.setItemId(1L);
        orderDTO.setQuantity(5);
        orderDTO.setUser(UserMapper.userToUserDto(user));

        order1 = new Order();
        order1.setId(1L);
        order1.setQuantity(5);
        order1.setItem(item);
        order1.setUser(user);

        order2 = new Order();
        order2.setId(2L);
        order2.setQuantity(3);
        order2.setItem(item);
        order2.setUser(user);
    }

    @Test
    public void testRegisterOrder() {

        when(itemRepository.findById(any(Long.class))).thenReturn(Optional.of(item));
        when(stockMovementService.findByOrder(any(Order.class))).thenReturn(Arrays.asList(new StockMovement(), new StockMovement()));
        when(orderRepository.save(any())).thenReturn(order1);

        OrderDTO createdOrderDTO = orderService.register(orderDTO);

        assertEquals(orderDTO.getQuantity(), createdOrderDTO.getQuantity());
    }

    @Test
    public void testUpdateOrder() {

        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order1));
        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        OrderDTO updatedOrderDTO = orderService.update(order1.getId(), orderDTO);

        assertEquals(updatedOrderDTO.getQuantity(), 5);
    }

    @Test
    public void testFindAllOrders() {

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<OrderDTO> orders = orderService.findAll();

        assertNotNull(orders);
    }
}