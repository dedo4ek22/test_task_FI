package com.test_task.FI.DTO;

import com.test_task.FI.utils.ItemType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderItemDTO {

    @Min(0)
    private Long itemId;

    private String description;

    private String name;

    private double price;

    @NotBlank
    private ItemType itemType;

    @Min(1)
    private int quantity;

    List<OrderItemModifierDTO> orderItemModifiers;
}
