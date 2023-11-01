package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.StockMovementDTO;
import com.exercise.ordermanager.entity.Item;
import com.exercise.ordermanager.entity.Order;
import com.exercise.ordermanager.entity.StockMovement;
import com.exercise.ordermanager.mapper.StockMovementMapper;
import com.exercise.ordermanager.repository.OrderRepository;
import com.exercise.ordermanager.repository.StockMovementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    private static final Logger logger = LogManager.getLogger(StockMovementService.class);

    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Autowired
    @Lazy
    public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, StockMovementMapper stockMovementMapper, OrderRepository orderRepository, OrderService orderService) {
        this.stockMovementRepository = stockMovementRepository;
        this.stockMovementMapper = stockMovementMapper;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @Override
    public StockMovementDTO findById(Long id) {
        return stockMovementMapper.toStockMovementDTO(returnStockMovement(id));
    }

    @Override
    public List<StockMovementDTO> findAll() {
        return stockMovementMapper.toStockMovementsDTO(stockMovementRepository.findAll());
    }

    @Override
    public StockMovementDTO findByItem(Item item) {
        stockMovementRepository.findByItem(item);
        return stockMovementMapper.toStockMovementDTO(stockMovementRepository.findByItem(item));
    }

    @Override
    public List<StockMovement> findByOrder(Order order) {
        stockMovementRepository.findByOrder(order);
        return stockMovementRepository.findByOrder(order);
    }

    @Override
    public StockMovementDTO register(StockMovementDTO stockMovementDTO) {
        try {
            StockMovement stockMovement = stockMovementMapper.toStockMovement(stockMovementDTO);
            stockMovementRepository.save(stockMovement);
            attributeStockMovementToOrder(stockMovement);
            logger.info("Stock movement created. ID: " + stockMovement.getId());
            return stockMovementMapper.toStockMovementDTO(stockMovement);

        } catch (Exception e) {
            logger.error("Error creating stock movement: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public StockMovementDTO update(Long id, StockMovementDTO stockMovementDTO) {
        try {
            StockMovement stockMovement = returnStockMovement(id);
            if(Objects.nonNull(stockMovement)) {
                stockMovementMapper.update(stockMovement, stockMovementDTO);
                logger.info("Stock movement updated. ID: " + id);
                return stockMovementMapper.toStockMovementDTO(stockMovementRepository.save(stockMovement));
            } else {
                logger.error("Stock movement not found. ID: " + id);
                throw new ObjectNotFoundException("Stock movement not found. ID: " + id, stockMovement);
            }
        } catch (Exception e) {
            logger.error("Error updating stock movement: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String delete(Long id) {
        try {
            if(stockMovementRepository.existsById(id)) {
                stockMovementRepository.deleteById(id);
                logger.info("Stock movement deleted. ID: " + id);
                return "Stock Movement id:" +id+ "successfully deleted";
            } else {
                logger.error("Stock Movement not found. ID: " + id);
                return "Stock Movement id:" +id+ "not found";
            }
        } catch (Exception e) {
            logger.error("Error deleting stock Movement: " + e.getMessage(), e);
            throw e;
        }
    }

    private StockMovement returnStockMovement(Long id){
        return stockMovementRepository.findById(id).orElseThrow(()-> new RuntimeException("Stock Movement not found"));
    }

    private void attributeStockMovementToOrder(StockMovement stockMovement) {
        List<Order> incompleteOrders = orderService.findIncompleteOrders();
        for(Order order: incompleteOrders) {
            if (order.getItem().equals(stockMovement.getItem()) && order.getQuantity() <= stockMovement.getQuantity()) {
                order.setCompleted(true);
                orderRepository.save(order);
                stockMovement.setOrder(order);
                logger.info("Stock movement attributed to order ID: " + stockMovement.getOrder().getId());
            }
        }
    }
}
