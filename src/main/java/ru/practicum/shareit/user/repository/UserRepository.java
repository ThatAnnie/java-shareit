package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User createUser(User user);

    User updateUser(long userId, User user);

    void deleteUser(long userId);

    Optional<User> getUserById(long userId);

    List<User> getUsers();
}
