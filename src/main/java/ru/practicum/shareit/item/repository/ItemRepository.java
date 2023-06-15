package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item createItem(Item item);

    Item updateItem(Long itemId, Item item);

    Optional<Item> getItemById(Long itemId);

    List<Item> getUserItems(Long userId);

    List<Item> searchItem(String text);

    void deleteItem(Long itemId);
}
