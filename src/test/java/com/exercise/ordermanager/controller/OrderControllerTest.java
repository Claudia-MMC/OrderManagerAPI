package com.exercise.ordermanager.controller;

import com.exercise.ordermanager.dto.OrderDTO;
import com.exercise.ordermanager.dto.UserDTO;
import com.exercise.ordermanager.service.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl orderService;

    @Test
    public void testFindById() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setQuantity(5);
        orderDTO.setItemId(1L);
        orderDTO.setUser(new UserDTO());

        when(orderService.findById(any())).thenReturn(orderDTO);

        mockMvc.perform(get("/order/{id}", orderDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderDTO.getId()))
                .andReturn();
    }
}
