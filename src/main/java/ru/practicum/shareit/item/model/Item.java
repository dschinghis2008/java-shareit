package ru.practicum.shareit.item.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * // TODO .
 */
@Data
public class Item {
    private Integer id;

    @NotBlank(message = "name не может быть пустым")
    private String name;

    private String description;

    private Boolean available;

    private Integer owner;

    private String request;
}
