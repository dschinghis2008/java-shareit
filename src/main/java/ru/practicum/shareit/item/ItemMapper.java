package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

@Service
public class ItemMapper {
    public ItemDto toDto(Item item){
        ItemDto itemDto = new ItemDto();

            itemDto.setId(item.getId());

            itemDto.setName(item.getName());

            itemDto.setDescription(item.getDescription());

            itemDto.setAvailable(item.getAvailable());

            itemDto.setOwner(item.getOwner());

            itemDto.setRequest(item.getRequest());


        return itemDto;
    }

    public Item toItem(ItemDto itemDto){
        Item item = new Item();

            item.setId(itemDto.getId());

            item.setName(itemDto.getName());

            item.setDescription(itemDto.getDescription());

            item.setAvailable(itemDto.getAvailable());

            item.setOwner(itemDto.getOwner());

            item.setRequest(itemDto.getRequest());

        return item;
    }
}
