package com.test_task.FI.services;

import com.test_task.FI.DTO.OrderItemModifierDTO;
import com.test_task.FI.models.Modifier;
import com.test_task.FI.repositories.ModifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModifierService {

    private final ModifierRepository modifierRepository;

    @Autowired
    public ModifierService(ModifierRepository modifierRepository) {
        this.modifierRepository = modifierRepository;
    }

    public List<Modifier> getModifiers(List<OrderItemModifierDTO> orderItemModifiersDTO){

        Set<Long> modifierIds = getModifiersIds(orderItemModifiersDTO);

        if(!modifierIds.isEmpty()){
            return getAllByModifierIdsIn(modifierIds);
        }else return new ArrayList<>();
    }

    @Transactional(readOnly = true)
    public List<Modifier> getAllByModifierIdsIn(Set<Long> modifierIds){
        return modifierRepository.findAllByModifierIdIn(modifierIds);
    }

    private Set<Long> getModifiersIds(List<OrderItemModifierDTO> orderItemModifiersDTO){
        return orderItemModifiersDTO
                .stream()
                .map(OrderItemModifierDTO::getModifierId)
                .collect(Collectors.toSet());
    }
}
