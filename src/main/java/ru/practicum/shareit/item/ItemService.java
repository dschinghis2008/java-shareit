package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

public interface ItemService {
    Item add(Item itemDto);
    Item update(Item itemDto,Integer userId);
    Item getById(Integer id);

    Collection<Item> getAll(Integer userId);
    Collection<Item> getByNameOrDesc(String text);
    void delete(Integer itemId,Integer userId);
}
