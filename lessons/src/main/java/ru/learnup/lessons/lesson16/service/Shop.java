package ru.learnup.lessons.lesson16.service;

import org.springframework.stereotype.Service;

@Service
public interface Shop {
    void getCatalog();
    void getDescriptionByName(String name);
    void addProductToBasket(String name, int count);
    void buy();
    void getBasket();
}
