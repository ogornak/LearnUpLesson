package ru.learnup.lessons.lesson16.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Market.Product")
public class ProductEntity implements Cloneable{
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @OneToOne
    @JoinColumn(name = "id")
    private StoreEntity storeEntity;

    public ProductEntity(){}

    public ProductEntity(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
