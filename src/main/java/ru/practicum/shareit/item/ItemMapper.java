package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

@Slf4j
@Service
public class ItemMapper {
    public ItemDto toItemDto(Item item){
        ItemDto itemDto = new ItemDto();
        try{
            itemDto.setId(item.getId());
        } catch (NullPointerException e){
            log.info("id is null");
        }
        try{
            itemDto.setName(item.getName());
        } catch (NullPointerException e){
            log.info("name is null");
        }
        try{
            itemDto.setDescription(item.getDescription());
        } catch (NullPointerException e){
            log.info("description is null");
        }
        try{
            itemDto.setAvailable(item.getAvailable());
        } catch (NullPointerException e){
            log.info("available is null");
        }
        try{
            itemDto.setOwner(item.getOwner());
        } catch (NullPointerException e){
            log.info("owner is null");
        }
        try{
            itemDto.setRequest(item.getRequest());
        } catch (NullPointerException e){
            log.info("request is null");
        }

        return itemDto;
    }

    public Item toItem(ItemDto itemDto){
        Item item = new Item();
        try{
            item.setId(itemDto.getId());
        } catch (NullPointerException e){
            log.info("item: id is null");
        }
        try{
            item.setName(itemDto.getName());
        } catch (NullPointerException e){
            log.info("item: name isnull");
        }
        try{
            item.setDescription(itemDto.getDescription());
        } catch (NullPointerException e){
            log.info("item: description is null");
        }
        try{
            item.setAvailable(itemDto.getAvailable());
        } catch (NullPointerException e){
            log.info("item: available is null");
        }
        try{
            item.setOwner(itemDto.getOwner());
        } catch (NullPointerException e){
            log.info("item: owner is null");
        }
        try{
            item.setRequest(itemDto.getRequest());
        } catch (NullPointerException e){
            log.info("item: request is null");
        }

        return item;
    }
}
