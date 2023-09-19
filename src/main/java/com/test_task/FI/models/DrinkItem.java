package com.test_task.FI.models;

import com.test_task.FI.utils.ItemType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "drink_item")
@Getter
@Setter
public class DrinkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_item_id")
    private long drinkItemId;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private int calories;

    @Column(name = "price")
    private double price;

    @Column(name = "item_type")
    private ItemType itemType;

    @ManyToMany
    @JoinTable(
            name = "drink_item_modifier_group",
            joinColumns = @JoinColumn(name = "drink_item_id"),
            inverseJoinColumns = @JoinColumn(name = "modifier_group_id"))
    private List<ModifierGroup> modifierGroups;

}
