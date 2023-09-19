package com.test_task.FI.services;

import com.test_task.FI.models.DrinkItem;
import com.test_task.FI.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkItemService {

    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkItemService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Transactional(readOnly = true)
    public List<DrinkItem> getAll() {
        return drinkRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DrinkItem> findById(long id) {
        return drinkRepository.findById(id);
    }
}
