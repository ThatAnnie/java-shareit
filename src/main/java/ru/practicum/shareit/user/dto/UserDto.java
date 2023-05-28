package ru.practicum.shareit.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    @Email
    private String email;
}
