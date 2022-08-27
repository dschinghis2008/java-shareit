package ru.practicum.shareit.requests;

import java.util.Collection;
import java.util.List;

public interface ItemRequestService {
    ItemRequest add(ItemRequest itemRequest);

    Collection<ItemRequest> getAllOwn(Integer requestorId);

    ItemRequest getById(Integer requestId, Integer requestorId);

    Collection<ItemRequest> getAll(Integer requestorId, Integer page, Integer size);
}
