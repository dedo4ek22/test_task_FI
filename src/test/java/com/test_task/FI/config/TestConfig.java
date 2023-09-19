package com.test_task.FI.config;

import com.test_task.FI.repositories.ModifierRepository;
import com.test_task.FI.repositories.OrderItemRepository;
import com.test_task.FI.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    ModifierRepository modifierRepository() {
        return mock(ModifierRepository.class);
    }

    @Bean
    public ModifierService modifierService() {
        return new ModifierService(modifierRepository());
    }

    @Bean
    public OrderItemModifierService orderItemModifierService() {
        return new OrderItemModifierService(modifierService());
    }

    @Bean
    OrderItemRepository orderItemRepository(){
        return mock(OrderItemRepository.class);
    }

    @Bean
    public OrderItemService orderItemService() {
        return new OrderItemService(orderItemRepository(),
                menuItemService(),
                drinkItemService(),
                orderItemModifierService());
    }

    @Bean
    public MenuItemService menuItemService() {
        return mock(MenuItemService.class);
    }

    @Bean
    public DrinkItemService drinkItemService() {
        return mock(DrinkItemService.class);
    }

}
