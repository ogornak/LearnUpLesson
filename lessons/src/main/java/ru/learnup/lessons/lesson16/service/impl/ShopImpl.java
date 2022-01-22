package ru.learnup.lessons.lesson16.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.lessons.lesson16.annotation.Loggable;
import ru.learnup.lessons.lesson16.annotation.Notifiable;
import ru.learnup.lessons.lesson16.model.Basket;
import ru.learnup.lessons.lesson16.model.Store;
import ru.learnup.lessons.lesson16.repository.MarketRepository;
import ru.learnup.lessons.lesson16.service.Shop;

import java.util.List;
import java.util.Optional;

@Service
public class ShopImpl implements Shop {
    private MarketRepository marketRepository;

    @Autowired
    public ShopImpl(MarketRepository marketRepository){
        this.marketRepository = marketRepository;
    }

    @Override
    @Loggable
    public List<Store> getCatalog(){
        return marketRepository.findAll();
    }

    @Override
    @Loggable
    public List<Basket> getBasket(){
        return marketRepository.findAllBasket();
    }

    @Override
    @Loggable
    public String getDescriptionByName(String name) {
        return marketRepository.findDescriptionByName(name);
    }

    @Override
    @Loggable
    @Transactional(
            timeout = 5,
            rollbackFor = {RuntimeException.class}
    )
    public void addProductToBasket(String name, int count) throws Exception {
        Basket basket = new Basket(name, count);
        Optional<Store> storeByName = marketRepository.findById(name);
        Store store = storeByName.get();
        if(store.getCount() < count){
            throw new Exception("Count error");
        }
        store.setCount(store.getCount() - count);

        editStoreAndBasket(store, basket);
    }

    void editStoreAndBasket(Store store, Basket basket) {
        Basket basketByName = marketRepository.findBasketByName(basket.getName());
        if(basketByName != null){
            basket = basketByName;
            basket.setCount(basket.getCount() + 1);
            marketRepository.updateBasket(basket);
        }
        else {
            marketRepository.saveBasket(basket);
        }

        marketRepository.save(store);
    }

    @Override
    @Loggable
    @Notifiable
    public void buy() {
        marketRepository.deleteBasketAll();
    }

}