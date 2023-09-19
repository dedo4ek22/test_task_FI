package com.test_task.FI;

import com.test_task.FI.models.DrinkItem;
import com.test_task.FI.models.MenuItem;
import com.test_task.FI.models.Modifier;
import com.test_task.FI.models.ModifierGroup;
import com.test_task.FI.repositories.DrinkRepository;
import com.test_task.FI.repositories.MenuItemRepository;
import com.test_task.FI.repositories.ModifierGroupRepository;
import com.test_task.FI.repositories.ModifierRepository;
import com.test_task.FI.utils.ItemType;
import com.test_task.FI.utils.KitchenType;
import com.test_task.FI.utils.MenuItemType;
import jakarta.annotation.PostConstruct;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FiApplication {

    private final MenuItemRepository menuItemRepository;
    private final DrinkRepository drinkRepository;
    private final ModifierGroupRepository modifierGroupRepository;
    private final ModifierRepository modifierRepository;

    @Autowired
    public FiApplication(MenuItemRepository menuItemRepository,
                         DrinkRepository drinkRepository,
                         ModifierGroupRepository modifierGroupRepository,
                         ModifierRepository modifierRepository) {
        this.menuItemRepository = menuItemRepository;
        this.drinkRepository = drinkRepository;
        this.modifierGroupRepository = modifierGroupRepository;
        this.modifierRepository = modifierRepository;
    }

    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBean() {
        return new DozerBeanMapper();
    }

    /**
     * initialize default models
     */
    @PostConstruct
    public void initialize() {

        MenuItem salad = new MenuItem();
        salad.setName("salad");
        salad.setCalories(300);
        salad.setMenuItemType(MenuItemType.MAIN);
        salad.setKitchenType(KitchenType.ITALIAN);
        salad.setPrice(7.99);
        salad.setItemType(ItemType.MENU);

        MenuItem soup = new MenuItem();
        soup.setName("soup");
        soup.setCalories(250);
        soup.setMenuItemType(MenuItemType.MAIN);
        soup.setKitchenType(KitchenType.POLISH);
        soup.setPrice(5.99);
        soup.setItemType(ItemType.MENU);

        MenuItem taco = new MenuItem();
        taco.setName("taco");
        taco.setCalories(250);
        taco.setMenuItemType(MenuItemType.MAIN);
        taco.setKitchenType(KitchenType.MEXICAN);
        taco.setPrice(4.99);
        taco.setItemType(ItemType.MENU);

        MenuItem cake = new MenuItem();
        cake.setName("cake");
        cake.setCalories(400);
        cake.setMenuItemType(MenuItemType.DESERT);
        cake.setKitchenType(KitchenType.POLISH);
        cake.setPrice(3.99);
        cake.setItemType(ItemType.MENU);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(salad);
        menuItems.add(soup);
        menuItems.add(taco);
        menuItems.add(cake);

        menuItemRepository.saveAll(menuItems);

        Modifier addIce = new Modifier();
        addIce.setName("add Ice");
        addIce.setPrice(0.49);

        Modifier addLemon = new Modifier();
        addLemon.setName("add lemon");
        addLemon.setPrice(1.00);

        List<Modifier> modifiers = new ArrayList<>();
        modifiers.add(addIce);
        modifiers.add(addLemon);

        modifiers = modifierRepository.saveAll(modifiers);

        ModifierGroup add = new ModifierGroup();
        add.setName("add modifiers");
        add.setModifiers(modifiers);
        add.setModifiers(modifiers);

        List<ModifierGroup> modifierGroups = new ArrayList<>();
        modifierGroups.add(add);

        modifierGroups = modifierGroupRepository.saveAll(modifierGroups);

        DrinkItem soda1 = new DrinkItem();
        soda1.setName("Fanta");
        soda1.setCalories(400);
        soda1.setPrice(2.49);
        soda1.setModifierGroups(modifierGroups);
        soda1.setItemType(ItemType.DRINK);

        DrinkItem soda2 = new DrinkItem();
        soda2.setName("Sprite");
        soda2.setCalories(400);
        soda2.setPrice(2.49);
        soda2.setModifierGroups(modifierGroups);
        soda2.setItemType(ItemType.DRINK);

        DrinkItem juice = new DrinkItem();
        juice.setName("Orange juice");
        juice.setCalories(100);
        juice.setPrice(3.49);
        juice.setModifierGroups(modifierGroups);
        juice.setItemType(ItemType.DRINK);

        List<DrinkItem> drinkItems = new ArrayList<>();
        drinkItems.add(soda1);
        drinkItems.add(soda2);
        drinkItems.add(juice);

        drinkRepository.saveAll(drinkItems);
    }

    public static void main(String[] args) {
        SpringApplication.run(FiApplication.class, args);
    }

}
