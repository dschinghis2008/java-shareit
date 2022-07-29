package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
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
    private final ItemMapper itemMapper;

    @PostMapping
    public ItemDto add(@RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        itemDto.setOwner(userId);
        Item item = itemMapper.toItem(itemDto);
        return itemMapper.toDto(itemService.add(item));
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable Integer itemId,
                          @RequestHeader("X-Sharer-User-Id") Integer userId) {
        itemDto.setOwner(userId);
        Item item = itemMapper.toItem(itemDto);
        return itemMapper.toDto(itemService.update(item, itemId));
    }

    @GetMapping("/{itemId}")
    public ItemDto getById(@PathVariable Integer itemId) {
        return itemMapper.toDto(itemService.getById(itemId));
    }

    @GetMapping
    public Collection<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        ArrayList<ItemDto> listDto = new ArrayList<>();
        for (Item item : itemService.getAll(userId)) {
            listDto.add(itemMapper.toDto(item));
        }
        return listDto;
    }

    @GetMapping("/search")
    public Collection<ItemDto> getByNameOrDesc(@RequestParam String text) {
        ArrayList<ItemDto> listDto = new ArrayList<>();
        for (Item item : itemService.getByNameOrDesc(text)) {
            listDto.add(itemMapper.toDto(item));
        }
        return listDto;
    }
}
