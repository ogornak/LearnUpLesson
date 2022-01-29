package ru.learnup.lessons.lesson16.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {
    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    public ProductDto(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
