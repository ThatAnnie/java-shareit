package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EntityAlreadyExistException;
import ru.practicum.shareit.exception.EntityNotExistException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser: {}", userDto);
        if (userDto.getEmail() == null) {
            log.warn("can't create user with no email");
            throw new ValidationException("Невозможно создать пользователя без email");
        }
        if (userRepository.getUsers().stream().anyMatch(user -> user.getEmail().equals(userDto.getEmail()))) {
            log.warn("user with email {} already exist", userDto.getEmail());
            throw new EntityAlreadyExistException(String.format("Пользователь с email=%s уже существует.", userDto.getEmail()));
        }
        User user = userRepository.createUser(UserMapper.userDtoToUser(userDto));
        return UserMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        log.info("updateUser: {}", userDto);
        User updateUser = userRepository.getUserById(userId).orElseThrow(() -> {
            log.warn("user with id={} not exist", userId);
            throw new EntityNotExistException(String.format("Пользователь с id=%d не существует.", userId));
        });
        if (userDto.getEmail() != null && !userDto.getEmail().equals(updateUser.getEmail())) {
            if (userRepository.getUsers().stream().anyMatch(user -> user.getEmail().equals(userDto.getEmail()))) {
                log.info("Пользователь с email {} уже существует", userDto.getEmail());
                throw new EntityAlreadyExistException(String.format("Пользователь с email=%s уже существует.", userDto.getEmail()));
            }
            updateUser.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null) {
            updateUser.setName(userDto.getName());
        }
        return UserMapper.userToUserDto(userRepository.updateUser(userId, updateUser));
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("deleteUser with id={}", userId);
        userRepository.getUserById(userId).orElseThrow(() -> {
            log.warn("user with id={} not exist", userId);
            throw new EntityNotExistException(String.format("Пользователь с id=%d не существует.", userId));
        });
        userRepository.deleteUser(userId);
    }

    @Override
    public UserDto getUserById(Long userId) {
        log.info("getUserById with id={}", userId);
        User user = userRepository.getUserById(userId).orElseThrow(() -> {
            log.warn("user with id={} not exist", userId);
            throw new EntityNotExistException(String.format("Пользователь с id=%d не существует.", userId));
        });
        return UserMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("getUsers");
        return userRepository.getUsers().stream()
                .map(UserMapper::userToUserDto)
                .collect(Collectors.toList());
    }
}
