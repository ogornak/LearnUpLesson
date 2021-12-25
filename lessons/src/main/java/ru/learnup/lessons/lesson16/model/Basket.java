package ru.learnup.lessons.lesson16.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Market.Basket")
public class Basket {
    @Id
    @Column(name = "Name")
    private String name;
    @Column(name = "Count")
    private int count;

    public Basket(){}

    public Basket(String name, int count){
        this.name = name;
        this.count = count;
    }
}
