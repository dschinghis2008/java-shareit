package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

/**
 * // TODO .
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public Item add(@RequestBody Item itemDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        itemDto.setOwner(userId);
        try {
            return itemService.add(itemDto);
        } catch (NullPointerException e) {
            //log.info("return null? >>>>>{}",itemDto.toString());
            return itemDto;
        }

    }

    @PatchMapping("/{itemId}")
    public Item update(@RequestBody Item itemDto, @PathVariable Integer itemId
            , @RequestHeader("X-Sharer-User-Id") Integer userId) {
        itemDto.setOwner(userId);
        return itemService.update(itemDto, itemId);
    }

    @GetMapping("/{itemId}")
    public Item getById(@PathVariable Integer itemId) {
        return itemService.getById(itemId);
    }

    @GetMapping
    public Collection<Item> getAll(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getAll(Math.toIntExact(userId));
    }

    @GetMapping("/search")
    public Collection<Item> getByNameOrDesc(@RequestParam String text) {
        return itemService.getByNameOrDesc(text);
    }
}
