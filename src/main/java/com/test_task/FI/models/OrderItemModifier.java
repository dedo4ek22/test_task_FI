package com.test_task.FI.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item_modifier")
@Getter
@Setter
@EqualsAndHashCode
public class OrderItemModifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_modifier_id")
    private long orderItemModifierId;

    @ManyToOne
    @JoinColumn(name = "modifier_id")
    private Modifier modifier;

    @Column(name = "item_price")
    private double itemPrice;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;
}
