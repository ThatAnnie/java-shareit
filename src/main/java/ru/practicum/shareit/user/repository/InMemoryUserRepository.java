package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private long generatedUserId;

    @Override
    public User createUser(User user) {
        user.setId(generateId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(Long userId, User user) {
        user.setId(userId);
        users.put(userId, user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        users.remove(userId);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        if (users.containsKey(userId)) {
            return Optional.ofNullable(users.get(userId));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    private long generateId() {
        return ++generatedUserId;
    }
}
