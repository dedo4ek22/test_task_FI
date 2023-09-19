package com.test_task.FI.controllers;

import com.test_task.FI.DTO.MenuItemDTO;
import com.test_task.FI.models.MenuItem;
import com.test_task.FI.services.MenuItemService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/1/menuItem")
public class MenuItemController {

    public final MenuItemService menuItemService;
    public final Mapper mapper;

    @Autowired
    public MenuItemController(MenuItemService menuItemService,
                              Mapper mapper) {
        this.menuItemService = menuItemService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getAllMenu(){

        List<MenuItem> menuItems = menuItemService.getAll();

        List<MenuItemDTO> result = menuItems.stream()
                .map(menuItem -> mapper.map(menuItem, MenuItemDTO.class))
                .toList();

        return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
    }

}
