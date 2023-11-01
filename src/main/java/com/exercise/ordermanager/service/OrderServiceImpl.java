package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.OrderCompletionStatusDTO;
import com.exercise.ordermanager.dto.OrderDTO;
import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.entity.Order;
import com.exercise.ordermanager.entity.StockMovement;
import com.exercise.ordermanager.entity.User;
import com.exercise.ordermanager.mapper.OrderMapper;
import com.exercise.ordermanager.mapper.UserMapper;
import com.exercise.ordermanager.repository.ItemRepository;
import com.exercise.ordermanager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    //private final EmailService emailService;
    private final StockMovementService stockMovementService;


    @Override
    public OrderDTO findById(Long id) {
        return OrderMapper.orderToOrderDto(returnOrder(id));
    }

    @Override
    public List<OrderDTO> findAll() {
        return OrderMapper.orderListToOrderDtoList(orderRepository.findAll());
    }

    @Override
    public OrderDTO register(OrderDTO orderDTO) {
        try {
            Order order = OrderMapper.orderDtoToOrder(orderDTO);
            Item item = itemRepository.findById(orderDTO.getItemId()).orElseThrow(() -> new ObjectNotFoundException("Item not found", orderDTO.getItemId()));;
            User user = UserMapper.userDtoToUser(orderDTO.getUser());
            order.setItem(item);
            order.setQuantity(orderDTO.getQuantity());
            order.setUser(user);
            order.setCreationDate(LocalDateTime.now());
            order.setCompleted(false);

            Order newOrder = orderRepository.save(order);

            this.satisfyOrder(newOrder);
            logger.info("Order created. ID: " + newOrder.getId());
            return OrderMapper.orderToOrderDto(newOrder);

        } catch (Exception e) {
            logger.error("Error creating order: " + e.getMessage(), e);
            throw e;
        }
    }

    private void satisfyOrder(Order order) {
        Item item = order.getItem();

        if(!Objects.isNull(item) && item.getStockQuantity() >= order.getQuantity()) {
            item.setStockQuantity(item.getStockQuantity() - order.getQuantity());
            itemRepository.save(item);
            order.setCompleted(true);

            // Associate the stock movement with the order
            List<StockMovement> stockMovements = stockMovementService.findByOrder(order);
            order.setStockMovements(stockMovements);

            orderRepository.save(order);
            logger.info("Order completed. ID: " + order.getId());

            // Send an email notification to the user
            //emailService.sendOrderNotification(order.getUser().getEmail(), "Order Confirmation", "Your order has been completed.");
            //logger.info("Email sent for order ID: " + order.getId());
        } else {
            logger.warn("Insufficient stock to complete the order. ID: " + order.getId());
        }
    }

    @Override
    public List<StockMovement> getStockMovementsForOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (Objects.nonNull(order) && order.isCompleted()) {
            return stockMovementService.findByOrder(order);
        }
        return Collections.emptyList();
    }

    public List<OrderCompletionStatusDTO> getCurrentCompletionStatus() {
        List<Order> orders = orderRepository.findAll();
        List<OrderCompletionStatusDTO> completionStatusList = new ArrayList<>();

        for (Order order : orders) {
            OrderCompletionStatusDTO completionStatus = new OrderCompletionStatusDTO();
            completionStatus.setOrderId(order.getId());
            completionStatus.setCompleted(order.isCompleted());

            if (order.isCompleted()) {
                List<StockMovement> stockMovements = stockMovementService.findByOrder(order);
                completionStatus.setStockMovements(stockMovements);
            }

            completionStatusList.add(completionStatus);
        }

        return completionStatusList;
    }

    @Override
    public OrderDTO update(Long id, OrderDTO orderDTO) {
        try {
            Order order = returnOrder(id);
            if(Objects.nonNull(order)) {
                OrderMapper.update(order, orderDTO);
                logger.info("Order updated. ID: " + id);
                return OrderMapper.orderToOrderDto(orderRepository.save(order));
            } else {
                logger.error("Order not found. ID: " + id);
                throw new ObjectNotFoundException("Item not found: ID=" + id, order);
            }
        } catch (Exception e) {
            logger.error("Error updating order: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String delete(Long id) {
        try {
            if(orderRepository.existsById(id)) {
                orderRepository.deleteById(id);
                logger.info("Order deleted. ID: " + id);
                return "Order id:" +id+ "successfully deleted";
            } else {
                logger.error("Order not found. ID: " + id);
                return "Order id:" +id+ "not found";
            }
        } catch (Exception e) {
            logger.error("Error deleting order: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Order> findIncompleteOrders() {
        return orderRepository.findByCompletedFalse();
    }

    private Order returnOrder (Long id) {
       return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
    }
}
