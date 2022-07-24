package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

public interface ItemService {
    ItemDto add(ItemDto itemDto);
    ItemDto update(ItemDto itemDto,Integer userId);
    ItemDto getById(Integer id);

    Collection<ItemDto> getAll(Integer userId);
    Collection<ItemDto> getByNameOrDesc(String text);
    void delete(Integer itemId,Integer userId);
}
