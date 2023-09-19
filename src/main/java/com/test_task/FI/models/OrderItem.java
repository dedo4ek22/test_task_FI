package com.test_task.FI.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@EqualsAndHashCode
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private long orderItemId;

    @ManyToOne
    private MenuItem menuItem;

    @ManyToOne
    private DrinkItem drinkItem;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "item_price")
    private double itemPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItemModifier> orderItemModifier;
}
