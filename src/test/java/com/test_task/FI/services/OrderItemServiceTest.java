package com.test_task.FI.services;


import com.test_task.FI.DTO.OrderItemDTO;
import com.test_task.FI.config.TestConfig;
import com.test_task.FI.models.MenuItem;
import com.test_task.FI.models.OrderItem;
import com.test_task.FI.repositories.OrderItemRepository;
import com.test_task.FI.utils.ItemType;
import com.test_task.FI.utils.KitchenType;
import com.test_task.FI.utils.MenuItemType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class OrderItemServiceTest {

    @Autowired
    MenuItemService menuItemService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    DrinkItemService drinkItemService;
    @Autowired
    OrderItemRepository orderItemRepository;

    public static List<OrderItemDTO> orderItemsDTO = new ArrayList<>();
    public static MenuItem salad = new MenuItem();
    public static List<OrderItem> orderItems = new ArrayList<>();

    @BeforeAll
    public static void init(){

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setItemId(1L);
        orderItemDTO.setItemType(ItemType.MENU);
        orderItemDTO.setQuantity(2);

        orderItemsDTO.add(orderItemDTO);

        salad.setMenuItemId(1L);
        salad.setName("salad");
        salad.setCalories(300);
        salad.setMenuItemType(MenuItemType.MAIN);
        salad.setKitchenType(KitchenType.ITALIAN);
        salad.setPrice(7.99);
        salad.setItemType(ItemType.MENU);

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(salad);
        orderItem.setQuantity(2);
        orderItem.setItemPrice(salad.getPrice());

        orderItems.add(orderItem);
    }

    @Test
    void createOrderItems(){
        when(menuItemService.findById(1L)).thenReturn(Optional.of(salad));
        when(orderItemRepository.saveAll(any())).thenAnswer(i -> i.getArguments()[0]);
        assertEquals(orderItemService.createOrderItems(orderItemsDTO), orderItems);

    }

}