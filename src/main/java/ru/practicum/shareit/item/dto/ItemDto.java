package ru.practicum.shareit.item.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.validationgroup.CreateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ItemDto {
    private Long id;
    @NotBlank(groups = CreateGroup.class)
    private String name;
    @NotBlank(groups = CreateGroup.class)
    private String description;
    @NotNull(groups = CreateGroup.class)
    private Boolean available;
}