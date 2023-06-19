package ru.practicum.shareit.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.user.validationgroup.CreateGroup;
import ru.practicum.shareit.user.validationgroup.UpdateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(groups = CreateGroup.class)
    @Size(max = 50)
    private String name;
    @Email(groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(groups = CreateGroup.class)
    @Size(max = 50)
    private String email;
}
