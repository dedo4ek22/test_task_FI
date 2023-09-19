package com.test_task.FI.models;

import com.test_task.FI.utils.ItemType;
import com.test_task.FI.utils.KitchenType;
import com.test_task.FI.utils.MenuItemType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private long menuItemId;

    @Column(name = "name")
    private String name;

    @Column(name = "menu_item_type")
    private MenuItemType menuItemType;

    @Column(name = "calories")
    private int calories;

    @Column(name = "kitchen_type")
    private KitchenType kitchenType;

    @Column(name = "price")
    private double price;

    @Column(name = "item_type")
    private ItemType itemType;
}
