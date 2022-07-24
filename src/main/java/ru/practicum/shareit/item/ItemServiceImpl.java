package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemDto add(ItemDto itemDto) {
        return itemMapper.toItemDto(itemRepository.add(itemMapper.toItem(itemDto)));
        //return itemRepository.add(itemDto);
    }

    @Override
    public ItemDto update(ItemDto itemDto, Integer userId) {
        return itemMapper.toItemDto(itemRepository.update(itemMapper.toItem(itemDto),userId));
    }

    @Override
    public ItemDto getById(Integer id) {
        return itemMapper.toItemDto(itemRepository.getById(id));
    }

    @Override
    public Collection<ItemDto> getByNameOrDesc(String text) {
        ArrayList<ItemDto> itemsDto = new ArrayList<>();
        for(Item item : itemRepository.getByNameOrDesc(text)){
            itemsDto.add(itemMapper.toItemDto(item));
        }
        return itemsDto;
    }

    @Override
    public Collection<ItemDto> getAll(Integer userId) {
        ArrayList<ItemDto> itemsDto = new ArrayList<>();
        for(Item item : itemRepository.getAll(userId)){
            itemsDto.add(itemMapper.toItemDto(item));
        }
        return itemsDto;
    }

    @Override
    public void delete(Integer itemId, Integer userId) {
        itemRepository.delete(itemId,userId);
    }
}
