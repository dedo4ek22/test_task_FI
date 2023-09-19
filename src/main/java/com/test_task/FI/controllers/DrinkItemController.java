package com.test_task.FI.controllers;

import com.test_task.FI.DTO.DrinkItemDTO;
import com.test_task.FI.models.DrinkItem;
import com.test_task.FI.services.DrinkItemService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/1/drinkItem")
public class DrinkItemController {

    private final DrinkItemService drinkItemService;
    private final Mapper mapper;

    @Autowired
    public DrinkItemController(DrinkItemService drinkItemService, Mapper mapper) {
        this.drinkItemService = drinkItemService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<DrinkItemDTO>> getAll(){

        List<DrinkItem> drinkItems = drinkItemService.getAll();

        List<DrinkItemDTO> result = drinkItems
                .stream()
                .map(item -> mapper.map(item, DrinkItemDTO.class))
                .toList();

        return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
    }

}
