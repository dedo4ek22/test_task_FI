package com.test_task.FI.controllers;

import com.test_task.FI.DTO.OrderDTO;
import com.test_task.FI.DTO.OrderItemDTO;
import com.test_task.FI.models.Order;
import com.test_task.FI.models.OrderItem;
import com.test_task.FI.services.OrderItemService;
import com.test_task.FI.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/1/order")
public class OrderController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderItemService orderItemService,
                           OrderService orderService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> crateOrder(@RequestBody @Valid List<OrderItemDTO> orderItemsDTO){

        Order order = new Order();
        List<OrderItem> orderItems = orderItemService.createOrderItems(orderItemsDTO);

        order.setOrderItems(orderItems);
        order = orderService.save(order);

        return new ResponseEntity<>(orderService.mapOrder(order), HttpStatusCode.valueOf(200));
    }

}
