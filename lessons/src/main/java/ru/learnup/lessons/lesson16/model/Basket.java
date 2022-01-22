package ru.learnup.lessons.lesson16.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Market.Basket")
public class Basket {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "ProductId")
    private int productId;

    @Column(name = "Count")
    private int count;

    public Basket(){}

    public Basket(int productId, int count){
        this.productId = productId;
        this.count = count;
    }
}
