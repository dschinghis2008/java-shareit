package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item add(Item item) {
        return itemRepository.add(item);
    }

    @Override
    public Item update(Item item, Integer userId) {
        return itemRepository.update(item, userId);
    }

    @Override
    public Item getById(Integer id) {
        return itemRepository.getById(id);
    }

    @Override
    public Collection<Item> getByNameOrDesc(String text) {
        return itemRepository.getByNameOrDesc(text);
    }

    @Override
    public Collection<Item> getAll(Integer userId) {
        return itemRepository.getAll(userId);
    }

    @Override
    public void delete(Integer itemId, Integer userId) {
        itemRepository.delete(itemId, userId);
    }
}
