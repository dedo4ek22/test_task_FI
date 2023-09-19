package com.test_task.FI.services;

import com.test_task.FI.models.MenuItem;
import com.test_task.FI.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    public final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Transactional(readOnly = true)
    public List<MenuItem> getAll(){
        return menuItemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<MenuItem> findById(long id) {
        return menuItemRepository.findById(id);
    }
}
