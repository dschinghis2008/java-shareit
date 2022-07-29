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

    @NotBlank(message = "description не может быть пустым")
    private String description;

    @NotBlank(message = "available не может быть пустым")
    private Boolean available;

    @NotBlank(message = "owner не может быть пустым")
    private Integer owner;

    private String request;
}
