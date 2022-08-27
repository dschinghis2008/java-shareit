package ru.practicum.shareit.requests;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.user.User;

@Service
public class ItemRequestMapper {

    public ItemRequestDto toDto(ItemRequest itemRequest){
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setId(itemRequest.getId());
        itemRequestDto.setDescription(itemRequest.getDescription());
        itemRequestDto.setRequestor(itemRequest.getRequestor());
        itemRequestDto.setCreated(itemRequest.getCreated());
        itemRequestDto.setItems(itemRequest.getItems());
        return itemRequestDto;
    }

    public ItemRequest toItemRequest(ItemRequestDto itemRequestDto, Integer requestorId){
        ItemRequest itemRequest = new ItemRequest();
        User user = new User();
        user.setId(requestorId);
        //user.setName(itemRequestDto.getRequestor().getName());
        //user.setEmail(itemRequestDto.getRequestor().getEmail());
        //itemRequest.setId(itemRequestDto.getId());
        itemRequest.setDescription(itemRequestDto.getDescription());
        itemRequest.setRequestor(user);
        //itemRequest.setCreated(itemRequestDto.getCreated());
        return itemRequest;
    }
}
