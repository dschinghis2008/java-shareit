package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item add(Item itemDto) {
        //log.info("add__itemDto: {}", itemDto.toString());
        //log.info("add_item: {}", itemMapper.toItem(itemDto).toString());
        //return itemMapper.toItemDto(itemRepository.add(itemMapper.toItem(itemDto)));
        return itemRepository.add(itemDto);
    }

    @Override
    public Item update(Item itemDto, Integer userId) {
        //return itemMapper.toItemDto(itemRepository.update(itemMapper.toItem(itemDto),userId));
        return itemRepository.update(itemDto,userId);
    }

    @Override
    public Item getById(Integer id) {
        //return itemMapper.toItemDto(itemRepository.getById(id));
        return itemRepository.getById(id);
    }

    @Override
    public Collection<Item> getByNameOrDesc(String text) {
        ArrayList<Item> itemsDto = new ArrayList<>();
        for(Item item : itemRepository.getByNameOrDesc(text)){
            //itemsDto.add(itemMapper.toItemDto(item));
            itemsDto.add(item);
        }
        return itemsDto;
    }

    @Override
    public Collection<Item> getAll(Integer userId) {
        ArrayList<Item> itemsDto = new ArrayList<>();
        for(Item item : itemRepository.getAll(userId)){
            //itemsDto.add(itemMapper.toItemDto(item));
            itemsDto.add(item);
        }
        return itemsDto;
    }

    @Override
    public void delete(Integer itemId, Integer userId) {
        itemRepository.delete(itemId,userId);
    }
}
