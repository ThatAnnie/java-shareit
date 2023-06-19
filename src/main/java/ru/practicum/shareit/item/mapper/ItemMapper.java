package ru.practicum.shareit.item.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.dto.ItemBookingDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

@UtilityClass
public class ItemMapper {
    public ItemDto itemToItemDto(Item item) {
        if (item == null) {
            return new ItemDto();
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setAvailable(item.getAvailable());
        return itemDto;
    }

    public Item itemDtoToItem(ItemDto itemDto) {
        if (itemDto == null) {
            return new Item();
        }
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        return item;
    }

    public ItemBookingDto itemToItemBookingDto(Item item) {
        if (item == null) {
            return new ItemBookingDto();
        }
        ItemBookingDto itemDto = new ItemBookingDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setAvailable(item.getAvailable());
        return itemDto;
    }
}
