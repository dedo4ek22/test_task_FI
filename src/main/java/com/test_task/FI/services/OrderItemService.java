package com.test_task.FI.services;

import com.test_task.FI.DTO.OrderItemDTO;
import com.test_task.FI.models.DrinkItem;
import com.test_task.FI.models.MenuItem;
import com.test_task.FI.models.OrderItem;
import com.test_task.FI.repositories.DrinkRepository;
import com.test_task.FI.repositories.MenuItemRepository;
import com.test_task.FI.repositories.OrderItemRepository;
import com.test_task.FI.utils.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MenuItemService menuItemService;
    private final DrinkItemService drinkItemService;
    private final OrderItemModifierService orderItemModifierService;


    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository,
                            MenuItemService menuItemService,
                            DrinkItemService drinkItemService,
                            OrderItemModifierService orderItemModifierService) {
        this.orderItemRepository = orderItemRepository;
        this.menuItemService = menuItemService;
        this.drinkItemService = drinkItemService;
        this.orderItemModifierService = orderItemModifierService;
    }

    public List<OrderItem> createOrderItems(List<OrderItemDTO> orderItemsDTO) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO orderItemDTO : orderItemsDTO) {

            OrderItem orderItem = new OrderItem();
            orderItem.setDescription(orderItemDTO.getDescription());
            orderItem.setQuantity(orderItemDTO.getQuantity());

            if (orderItemDTO.getItemType().equals(ItemType.MENU)) {

                Optional<MenuItem> menuItemOptional = menuItemService.findById(orderItemDTO.getItemId());

                if (menuItemOptional.isPresent()) {

                    MenuItem menuItem = menuItemOptional.get();

                    orderItem.setMenuItem(menuItem);
                    orderItem.setItemPrice(menuItem.getPrice());

                } else {
                    throw new RuntimeException("menu item not found");
                }

            } else if (orderItemDTO.getItemType().equals(ItemType.DRINK)) {

                Optional<DrinkItem> drinkItemOptional = drinkItemService.findById(orderItemDTO.getItemId());

                if (drinkItemOptional.isPresent()) {

                    DrinkItem drinkItem = drinkItemOptional.get();

                    orderItem.setDrinkItem(drinkItem);
                    orderItem.setItemPrice(drinkItem.getPrice());

                    if (orderItemDTO.getOrderItemModifiers() != null && !orderItemDTO.getOrderItemModifiers().isEmpty()) {
                        orderItem.setOrderItemModifier(orderItemModifierService.fillOrderItemModifiers(orderItemDTO.getOrderItemModifiers(), orderItem));
                    }

                } else {
                    throw new RuntimeException("drink item not found");
                }

            } else {
                throw new RuntimeException("incorrect item type");
            }

            orderItems.add(orderItem);

        }

        orderItems = saveAll(orderItems);

        return orderItems;
    }


    @Transactional
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }
}
