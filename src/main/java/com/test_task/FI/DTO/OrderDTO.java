package com.test_task.FI.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private List<OrderItemDTO> orderItems;

    private double orderSum;
}
