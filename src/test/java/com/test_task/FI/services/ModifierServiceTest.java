package com.test_task.FI.services;

import com.test_task.FI.DTO.OrderItemModifierDTO;
import com.test_task.FI.config.TestConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class ModifierServiceTest {

    @Autowired
    ModifierService modifierService;

    public static List<OrderItemModifierDTO> orderItemModifiersDTO = new ArrayList<>();

    @BeforeAll
    public static void init(){

        OrderItemModifierDTO orderItemModifierDTO1 = new OrderItemModifierDTO();
        orderItemModifierDTO1.setModifierId(1);

        OrderItemModifierDTO orderItemModifierDTO2 = new OrderItemModifierDTO();
        orderItemModifierDTO2.setModifierId(2);

        orderItemModifiersDTO.add(orderItemModifierDTO1);
        orderItemModifiersDTO.add(orderItemModifierDTO2);
    }

    @Test
    void getModifiersIds() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getModifiersIds = ModifierService.class.getDeclaredMethod("getModifiersIds", List.class);
        getModifiersIds.setAccessible(true);

        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        ids.add(2L);

        assertEquals(getModifiersIds.invoke(modifierService, orderItemModifiersDTO), ids);
    }

}