package com.test_task.FI.services;

import com.test_task.FI.DTO.OrderItemModifierDTO;
import com.test_task.FI.config.TestConfig;
import com.test_task.FI.models.Modifier;
import com.test_task.FI.models.OrderItem;
import com.test_task.FI.models.OrderItemModifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class OrderItemModifierServiceTest {

    @Autowired
    OrderItemModifierService orderItemModifierService;

    public static OrderItem orderItem = new OrderItem();

    public static List<Modifier> modifiers = new ArrayList<>();

    public static List<OrderItemModifier> orderItemModifiers = new ArrayList<>();

    public static List<OrderItemModifierDTO> orderItemModifierDTOS = new ArrayList<>();

    @BeforeAll
    public static void init() {

        Modifier addIce = new Modifier();
        addIce.setName("add Ice");
        addIce.setPrice(0.49);

        Modifier addLemon = new Modifier();
        addLemon.setName("add lemon");
        addLemon.setPrice(1.00);

        modifiers.add(addIce);
        modifiers.add(addLemon);

        OrderItemModifier orderItemModifier1 = new OrderItemModifier();
        orderItemModifier1.setModifier(addIce);
        orderItemModifier1.setOrderItem(orderItem);
        orderItemModifier1.setItemPrice(addIce.getPrice());

        OrderItemModifier orderItemModifier2 = new OrderItemModifier();
        orderItemModifier2.setModifier(addLemon);
        orderItemModifier2.setOrderItem(orderItem);
        orderItemModifier2.setItemPrice(addLemon.getPrice());

        orderItemModifiers.add(orderItemModifier1);
        orderItemModifiers.add(orderItemModifier2);

        OrderItemModifierDTO orderItemModifierDTO1 = new OrderItemModifierDTO();
        orderItemModifierDTO1.setItemPrice(orderItemModifier1.getItemPrice());
        orderItemModifierDTO1.setName(orderItemModifier1.getModifier().getName());

        OrderItemModifierDTO orderItemModifierDTO2 = new OrderItemModifierDTO();
        orderItemModifierDTO2.setItemPrice(orderItemModifier2.getItemPrice());
        orderItemModifierDTO2.setName(orderItemModifier2.getModifier().getName());

        orderItemModifierDTOS.add(orderItemModifierDTO1);
        orderItemModifierDTOS.add(orderItemModifierDTO2);
    }

    @Test
    void mapOrderItemModifier() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method mapOrderItemModifier =
                OrderItemModifierService.class.getDeclaredMethod("mapOrderItemModifier", List.class, OrderItem.class);
        mapOrderItemModifier.setAccessible(true);

        assertEquals(mapOrderItemModifier.invoke(orderItemModifierService, modifiers, orderItem), orderItemModifiers);
    }

    @Test
    void mapOrderItemModifierDTO(){
        List<OrderItemModifierDTO> res = orderItemModifierService.mapOrderItemModifierDTO(orderItemModifiers);
        assertEquals(orderItemModifierDTOS, res);
    }


}