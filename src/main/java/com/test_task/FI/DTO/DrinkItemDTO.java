package com.test_task.FI.DTO;

import com.test_task.FI.utils.ItemType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DrinkItemDTO {

    private long drinkItemId;

    private String name;

    private int calories;

    private double price;

    private ItemType itemType;

    private List<ModifierGroupDTO> modifierGroups;
}
