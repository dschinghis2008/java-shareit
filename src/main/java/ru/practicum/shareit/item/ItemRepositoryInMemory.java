package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@Repository
@Component
@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryInMemory implements ItemRepository {
    private Map<Integer, Item> items = new HashMap<>();
    private Integer itemId = 0;

    private Integer getItemId() {
        return ++itemId;
    }

    @Override
    public Item add(Item item) {
        item.setId(getItemId());
        items.put(item.getId(), item);
        log.info("добавлена вещь /{}/",item.toString());
        return item;
    }

    @Override
    public Item update(Item item, Integer id) {
        if (items.get(id) == null || items.get(id).getOwner() != item.getOwner()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        try {
            items.get(id).setName(item.getName());
            items.get(id).setDescription(item.getDescription());
            items.get(id).setAvailable(item.getAvailable());
        } catch (NullPointerException e) {
            log.info("field for update not found, itemId={},/{}/", id, item.toString());
        }
        log.info("обновлена вещь /{}/",items.get(id).toString());
        return items.get(id);
    }

    @Override
    public Item getById(Integer id) {
        log.info("запрошена вещь /id={}/",id);
        return items.get(id);
    }

    @Override
    public Collection<Item> getAll(Integer userId) {
        ArrayList<Item> itemList = new ArrayList<>();
        for(Item item : items.values()){
            if(item.getOwner() == userId){
                itemList.add(item);
            }
        }
        log.info("запрошен список вещей владельца /id={}/",userId);
        return itemList;
    }

    @Override
    public Collection<Item> getByNameOrDesc(String text) {
        ArrayList<Item> itemList = new ArrayList<>();
        for(Item item : items.values()){
            if(item.getName().substring(0,text.length()-1).toLowerCase().equals(text.toLowerCase())
            || item.getDescription().substring(0,text.length()-1).toLowerCase().equals(text.toLowerCase())){
                itemList.add(item);
            }
        }
        log.info("запрошен поиск вещи по строке /text={}/, найдено {}",text,itemList.size());
        return itemList;
    }

    @Override
    public void delete(Integer itemId, Integer userId) {
        if (items.get(itemId) == null || items.get(itemId).getOwner() != userId) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        items.remove(itemId);
        log.info("удалена вещь /id={}/",itemId);
    }
}
