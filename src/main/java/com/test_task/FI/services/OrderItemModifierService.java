package com.test_task.FI.services;

import com.test_task.FI.DTO.OrderItemModifierDTO;
import com.test_task.FI.models.Modifier;
import com.test_task.FI.models.OrderItem;
import com.test_task.FI.models.OrderItemModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemModifierService {
    private final ModifierService modifierService;

    @Autowired
    public OrderItemModifierService(ModifierService modifierService) {
        this.modifierService = modifierService;
    }

    public List<OrderItemModifier> fillOrderItemModifiers(List<OrderItemModifierDTO> orderItemModifiersDTO, OrderItem orderItem) {

        List<Modifier> modifiers = modifierService.getModifiers(orderItemModifiersDTO);

        if (!modifiers.isEmpty()) {
            return mapOrderItemModifier(modifiers, orderItem);
        } else return new ArrayList<>();
    }

    public List<OrderItemModifierDTO> mapOrderItemModifierDTO(List<OrderItemModifier> orderItemModifiers) {

        List<OrderItemModifierDTO> orderItemModifiersDTO = new ArrayList<>();

        for (OrderItemModifier orderItemModifier : orderItemModifiers) {

            OrderItemModifierDTO orderItemModifierDTO = new OrderItemModifierDTO();
            orderItemModifierDTO.setItemPrice(orderItemModifier.getItemPrice());
            orderItemModifierDTO.setName(orderItemModifier.getModifier().getName());

            orderItemModifiersDTO.add(orderItemModifierDTO);
        }

        return orderItemModifiersDTO;
    }

    private List<OrderItemModifier> mapOrderItemModifier(List<Modifier> modifiers, OrderItem orderItem) {

        List<OrderItemModifier> orderItemModifiers = new ArrayList<>();

        for (Modifier modifier : modifiers) {

            OrderItemModifier orderItemModifier = new OrderItemModifier();
            orderItemModifier.setModifier(modifier);
            orderItemModifier.setOrderItem(orderItem);
            orderItemModifier.setItemPrice(modifier.getPrice());

            orderItemModifiers.add(orderItemModifier);
        }

        return orderItemModifiers;
    }

}
