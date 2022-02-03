package ru.learnup.lessons.lesson16.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductMessageBody implements Serializable {
    private int productId;
    private int count;

    @Override
    public String toString(){
        return "Товар с Id " + productId + " в количестве " + count + " шт.";
    }
}
