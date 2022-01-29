package ru.learnup.lessons.lesson16.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BasketDto {
    @JsonProperty
    private int productId;

    @JsonProperty
    private int count;

    public BasketDto(int productId, int count){
        this.productId = productId;
        this.count = count;
    }
}
