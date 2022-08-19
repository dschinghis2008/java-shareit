package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoDate;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
        itemDto.setId(itemId);
        itemDto.setOwner(userId);
        Item item = itemMapper.toItem(itemDto);
        return itemMapper.toDto(itemService.update(item, userId));
    }

    @GetMapping("/{itemId}")
    public ItemDtoDate getById(@PathVariable Integer itemId,@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.getItemDate(itemId, LocalDateTime.now(), userId);
    }

    @GetMapping
    public Collection<ItemDtoDate> getAll(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.getAll(userId);
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
