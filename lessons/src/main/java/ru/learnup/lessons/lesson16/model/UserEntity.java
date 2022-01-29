package ru.learnup.lessons.lesson16.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dbo.[User]")
public class UserEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String role;
}
