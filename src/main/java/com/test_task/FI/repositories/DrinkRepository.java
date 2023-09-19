package com.test_task.FI.repositories;

import com.test_task.FI.models.DrinkItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkItem, Long> {



}
