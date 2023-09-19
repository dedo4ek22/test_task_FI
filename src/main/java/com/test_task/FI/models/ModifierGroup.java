package com.test_task.FI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "modifier_group")
@Getter
@Setter
public class ModifierGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modifier_group_id")
    private long modifierGroupId;

    @Column(name = "name")
    private String name;

    @OneToMany()
    private List<Modifier> modifiers;

    @ManyToMany(mappedBy = "modifierGroups")
    private List<DrinkItem> drinkItems;

}
