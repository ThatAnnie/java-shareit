package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User createUser(User user);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);

    Optional<User> getUserById(Long userId);

    List<User> getUsers();
}
