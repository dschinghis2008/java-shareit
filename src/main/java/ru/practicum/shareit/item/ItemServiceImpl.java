package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public Item add(Item item) {
        if (item.getName().isEmpty() || item.getAvailable() == null || item.getDescription() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findById(item.getOwner()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        log.info("добавлена вещь /{}/", item.toString());
        return itemRepository.save(item);
    }

    @Override
    public Item update(Item item, Integer userId) {
        if (itemRepository.findById(item.getId()).isEmpty() || userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (itemRepository.findById(item.getId()).orElseThrow().getOwner() != userId) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Item itemUpd = itemRepository.findById(item.getId()).orElseThrow();
        if (item.getName() != null) {
            itemUpd.setName(item.getName());
        }
        if (item.getDescription() != null) {
            itemUpd.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            itemUpd.setAvailable(item.getAvailable());
        }
        if (item.getRequest() != null) {
            itemUpd.setRequest(item.getRequest());
        }
        log.info("обновлена вещь newValue=/{}/", itemUpd.toString());
        return itemRepository.save(itemUpd);
    }

    @Override
    public Item getById(Integer id) {
        log.info("запрошена вещь id={}", id);
        return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Collection<Item> getByNameOrDesc(String text) {
        if (text.equals("")) {
            return new ArrayList<Item>();
        }
        ArrayList<Item> listSearch = (ArrayList<Item>) itemRepository.findByNameOrDesc(text);
        log.info("запрошен поиск по строке /{}/ ,найдено /{}/", text, listSearch.size());
        return listSearch;
    }

    @Override
    public Collection<Item> getAll(Integer userId) {
        log.info("запрошены вещи владельца /{}/", userId);
        return itemRepository.findAllByOwner(userId);
    }

    @Override
    public void delete(Integer itemId, Integer userId) {
    }
}
