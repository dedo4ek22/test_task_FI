package com.test_task.FI.services;

import com.test_task.FI.DTO.OrderDTO;
import com.test_task.FI.DTO.OrderItemDTO;
import com.test_task.FI.DTO.OrderItemModifierDTO;
import com.test_task.FI.models.Order;
import com.test_task.FI.models.OrderItem;
import com.test_task.FI.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemModifierService orderItemModifierService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderItemModifierService orderItemModifierService) {
        this.orderRepository = orderRepository;
        this.orderItemModifierService = orderItemModifierService;
    }

    public OrderDTO mapOrder(Order order){

        OrderDTO result = new OrderDTO();

        double orderSum = 0;
        List<OrderItemDTO> orderItems = new ArrayList<>();

        for (OrderItem orderItem : order.getOrderItems()){
            OrderItemDTO item = new OrderItemDTO();

            if(orderItem.getMenuItem() != null){
                item.setName(orderItem.getMenuItem().getName());
            }else {
                item.setName(orderItem.getDrinkItem().getName());
            }
            item.setDescription(orderItem.getDescription());
            item.setPrice(orderItem.getItemPrice());
            item.setQuantity(orderItem.getQuantity());

            if(orderItem.getOrderItemModifier() != null && !orderItem.getOrderItemModifier().isEmpty()) {
                item.setOrderItemModifiers(orderItemModifierService.mapOrderItemModifierDTO(orderItem.getOrderItemModifier()));
            }

            orderSum += item.getPrice() * item.getQuantity();

            if(item.getOrderItemModifiers() != null && !item.getOrderItemModifiers().isEmpty()){
                for (OrderItemModifierDTO orderItemModifierDTO : item.getOrderItemModifiers()){
                    orderSum += orderItemModifierDTO.getItemPrice() * item.getQuantity();
                }
            }

            orderItems.add(item);
        }

        result.setOrderSum(orderSum);
        result.setOrderItems(orderItems);

        return result;
    }

    @Transactional
    public Order save(Order order){
        return orderRepository.save(order);
    }

}
