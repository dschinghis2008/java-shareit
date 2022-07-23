package ru.practicum.shareit.item;

import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.model.Item;

/**
 * // TODO .
 */
@RestController
@RequestMapping("/items")
public class ItemController {

    @PostMapping
    public Item add(@RequestHeader("X-Later-User-Id") Long userId,
                    @RequestBody Item item) {
        return null;//itemService.addNewItem(userId, item);
    }
}
