package ru.learnup.lessons.lesson16.service;

import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.model.Basket;
import ru.learnup.lessons.lesson16.model.Store;

import java.util.List;

public interface Shop {
    List<Store> getCatalog();
    String getDescriptionByName(String name);
    void addProductToBasket(String name, int count);
    void buy();
    List<Basket> getBasket();
}
