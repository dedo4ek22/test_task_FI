package com.test_task.FI.repositories;

import com.test_task.FI.models.Modifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ModifierRepository extends JpaRepository<Modifier, Long> {

    List<Modifier> findAllByModifierIdIn(Set<Long> ids);

}
