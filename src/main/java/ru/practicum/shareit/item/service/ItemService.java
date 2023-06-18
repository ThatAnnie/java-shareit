package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemBookingDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(Long userId, ItemDto item);

    ItemDto updateItem(Long userId, Long itemId, ItemDto item);

    void deleteItem(Long userId, Long itemId);

    ItemDto getItemById(Long userId, Long itemId);

    List<ItemBookingDto> getUserItems(Long userId);

    List<ItemDto> searchItem(String text);

    CommentDto createComment(Long userId, Long itemId, CommentDto commentDto);
}
