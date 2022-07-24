package ru.practicum.shareit.item.dto;

import lombok.Data;

/**
 * // TODO .
 */
@Data
public class ItemDto {
    private Integer id;

    private String name;

    private String description;

    private Boolean available;

    private Integer owner;

    private String request;

}
