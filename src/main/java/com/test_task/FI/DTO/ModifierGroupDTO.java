package com.test_task.FI.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModifierGroupDTO {

    private long modifierGroupId;

    private String name;

    private List<ModifierDTO> modifiers;
}
