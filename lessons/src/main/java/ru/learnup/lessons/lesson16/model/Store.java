package ru.learnup.lessons.lesson16.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Market.Store")
public class Store {
    @Id
    @GeneratedValue
    private int id;

    //@Column(name = "ProductId")
    //private int productId;

    @Column(name = "Price")
    private int price;

    @Column(name = "Count")
    private int count;

    @JoinColumn(name = "productId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;

    @Override
    public String toString(){
        return product.getName() + " " + price + " " + count + " " + product.getDescription();
    }
}
