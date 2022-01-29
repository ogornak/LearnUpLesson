package ru.learnup.lessons.lesson16.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StoreDto {
    @JsonProperty
    private int id;

    @JsonProperty
    private int price;

    @JsonProperty
    private int count;

    @JsonProperty
    private ProductDto product;

    public StoreDto(int id, int price, int count, ProductDto product){
        this.id = id;
        this.price = price;
        this.count = count;
        this.product = product;
    }
}
