package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EntityNotExistException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ItemDto createItem(Long userId, ItemDto itemDto) {
        log.info("createItem: {}, userId={}", itemDto, userId);
        userRepository.getUserById(userId).orElseThrow(() -> {
            log.warn("user with id={} not exist", userId);
            throw new EntityNotExistException(String.format("Пользователь с id=%d не существует.", userId));
        });
        Item item = ItemMapper.itemDtoToItem(itemDto);
        item.setOwner(userRepository.getUserById(userId).get());
        return ItemMapper.itemToItemDto(itemRepository.createItem(item));
    }

    @Override
    public ItemDto updateItem(Long userId, Long itemId, ItemDto itemDto) {
        log.info("updateItem: {}", itemDto);
        Item updateItem = itemRepository.getItemById(itemId).orElseThrow(() -> {
            log.warn("item with id={} not exist", itemId);
            throw new EntityNotExistException(String.format("Вещь с id=%d не существует.", itemId));
        });
        if (!updateItem.getOwner().getId().equals(userId)) {
            log.warn("user with id={} is not owner of item with id={}", userId, itemId);
            throw new EntityNotExistException(String.format("Пользователь с id=%d не владелец вещи.", userId));
        }
        if (itemDto.getName() != null) {
            updateItem.setName(itemDto.getName());
        }
        if (itemDto.getDescription() != null) {
            updateItem.setDescription(itemDto.getDescription());
        }
        if (itemDto.getAvailable() != null) {
            updateItem.setAvailable(itemDto.getAvailable());
        }
        return ItemMapper.itemToItemDto(itemRepository.updateItem(itemId, updateItem));
    }

    @Override
    public void deleteItem(Long userId, Long itemId) {
        log.info("deleteItem: id = {}, userId = {}", itemId, userId);
        Item deleteItem = itemRepository.getItemById(itemId).orElseThrow(() -> {
            log.warn("item with id={} not exist", itemId);
            throw new EntityNotExistException(String.format("Вещь с id=%d не существует.", itemId));
        });
        if (!deleteItem.getOwner().getId().equals(userId)) {
            log.warn("user with id={} is not owner of item with id={}", userId, itemId);
            throw new EntityNotExistException(String.format("Пользователь с id=%d не владелец вещи.", userId));
        }
        itemRepository.deleteItem(itemId);
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        log.info("getItemById with id={}", itemId);
        Item item = itemRepository.getItemById(itemId).orElseThrow(() -> {
            log.warn("item with id={} not exist", itemId);
            throw new EntityNotExistException(String.format("Вещь с id=%d не существует.", itemId));
        });
        return ItemMapper.itemToItemDto(item);
    }

    @Override
    public List<ItemDto> getUserItems(Long userId) {
        log.info("getUserItems by user with id={}", userId);
        userRepository.getUserById(userId).orElseThrow(() -> {
            log.warn("user with id={} not exist", userId);
            throw new EntityNotExistException(String.format("Пользователь с id=%d не существует.", userId));
        });
        return itemRepository.getUserItems(userId).stream()
                .map(ItemMapper::itemToItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItem(String text) {
        log.info("searchItem by text={}", text);
        if (text.isBlank()) {
            return new ArrayList<>();
        }
        return itemRepository.searchItem(text).stream()
                .map(ItemMapper::itemToItemDto)
                .collect(Collectors.toList());
    }
}
