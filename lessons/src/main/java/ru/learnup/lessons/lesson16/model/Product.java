package ru.learnup.lessons.lesson16.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Market.Product")
public class Product implements Cloneable{
    @Id
    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @OneToOne
    @JoinColumn(name = "Name")
    private Store store;
}
