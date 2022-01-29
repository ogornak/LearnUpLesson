package ru.learnup.lessons.lesson16.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Market.Store")
public class StoreEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Price")
    private int price;

    @Column(name = "Count")
    private int count;

    @JoinColumn(name = "productId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductEntity productEntity;

    public StoreEntity(){};

    public StoreEntity(int id, int price, int count, ProductEntity productEntity){
        this.id = id;
        this.price = price;
        this.count = count;
        this.productEntity = this.productEntity;
    }

    @Override
    public String toString(){
        return productEntity.getName() + " " + price + " " + count + " " + productEntity.getDescription();
    }
}
