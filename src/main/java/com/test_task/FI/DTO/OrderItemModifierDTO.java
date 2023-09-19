package com.test_task.FI.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class OrderItemModifierDTO {

    private long modifierId;

    private String name;

    private double itemPrice;
}
