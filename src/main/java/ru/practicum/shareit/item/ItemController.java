package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

/**
 * // TODO .
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    @PostMapping
    public ItemDto add(@RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        itemDto.setOwner(userId);
        try {
            return itemService.add(itemDto);
        } catch (NullPointerException e){
            System.out.println("return null>>>>>");
        }
        return null;
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable Integer itemId
            , @RequestHeader("X-Sharer-User-Id") Integer userId){
        itemDto.setOwner(userId);
        return itemService.update(itemDto,itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getById(@PathVariable Integer itemId){
        return itemService.getById(itemId);
    }

    @GetMapping
    public Collection<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") Long userId){
        return itemService.getAll(Math.toIntExact(userId));
    }

    @GetMapping("/search")
    public Collection<ItemDto> getByNameOrDesc(@RequestParam String text){
        return itemService.getByNameOrDesc(text);
    }
}
