package ru.learnup.lessons.lesson16.service;

import ru.learnup.lessons.lesson16.model.BasketEntity;
import ru.learnup.lessons.lesson16.model.StoreEntity;

import java.util.List;

public interface Shop {
    List<StoreEntity> getCatalog();
    String getDescriptionById(int id);
    void addProductToBasket(int productId, int count) throws Exception;
    void buy();
    void updateCount(int productId, int count);
    List<BasketEntity> getBasket();
    StoreEntity findById(int id);
}
