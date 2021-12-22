package ru.learnup.lessons.lesson16.model;

import lombok.Data;

@Data
public class Product implements Cloneable{
    private String name;
    private String description;
    private int price;
    private int count;

    public Product(String name, String description, int price, int count){
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
