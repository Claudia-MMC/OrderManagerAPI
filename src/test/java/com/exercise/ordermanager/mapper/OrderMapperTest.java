package com.exercise.ordermanager.mapper;

import com.exercise.ordermanager.dto.OrderDTO;
import com.exercise.ordermanager.dto.UserDTO;
import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.entity.Order;
import com.exercise.ordermanager.entity.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMapperTest {

    @Test
    public void testOrderToOrderDTO() {

        Item item = new Item();
        item.setId(1L);

        Order order = new Order();
        order.setId(1L);
        order.setQuantity(5);
        order.setItem(item);
        order.setUser(new User());


        OrderDTO expectedDTO = new OrderDTO();
        expectedDTO.setId(1L);
        expectedDTO.setQuantity(5);
        expectedDTO.setItemId(1L);
        expectedDTO.setUser(new UserDTO());

        OrderDTO mappedDTO = OrderMapper.orderToOrderDto(order);

        assertThat(mappedDTO).isEqualToComparingFieldByField(expectedDTO);
    }
}
