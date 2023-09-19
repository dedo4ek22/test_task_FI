package com.test_task.FI.DTO;

import com.test_task.FI.utils.ItemType;
import com.test_task.FI.utils.KitchenType;
import com.test_task.FI.utils.MenuItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemDTO {

    private long menuItemId;

    private String name;

    private MenuItemType menuItemType;

    private int calories;

    private KitchenType kitchenType;

    private double price;

    private ItemType itemType;

}
