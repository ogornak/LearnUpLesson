package ru.learnup.lessons.lesson16.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.annotation.Loggable;
import ru.learnup.lessons.lesson16.annotation.Notifiable;
import ru.learnup.lessons.lesson16.model.Basket;
import ru.learnup.lessons.lesson16.model.Store;
import ru.learnup.lessons.lesson16.repository.BasketRepository;
import ru.learnup.lessons.lesson16.repository.StoreRepository;
import ru.learnup.lessons.lesson16.service.Shop;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class ShopImp implements Shop {
    private StoreRepository storeRepository;
    private BasketRepository basketRepository;

    @Autowired
    public ShopImp(StoreRepository storeRepository, BasketRepository basketRepository){
        this.storeRepository = storeRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    @Loggable
    public List<Store> getCatalog(){
        return storeRepository.findAll();
    }

    @Override
    @Loggable
    public List<Basket> getBasket(){
        return basketRepository.findAll();
    }

    @Override
    @Loggable
    public String getDescriptionByName(String name){
        Optional<Store> storeByName = storeRepository.findById(name);
        if(storeByName.isPresent()){
            return storeByName.get().getProduct().getDescription();
        }

        return null;
    }

    @Override
    @Loggable
    public void addProductToBasket(String name, int count) {
        Basket basket = new Basket(name, count);
        Optional<Store> storeByName = storeRepository.findById(name);
        Store store = storeByName.get();
        if(store.getCount() < count){
            System.out.println("Error");
            return;
        }
        store.setCount(store.getCount() - count);

        Optional<Basket> basketByName = basketRepository.findById(name);
        if(basketByName.isPresent()){
            basket = basketByName.get();
            basket.setCount(basket.getCount() + 1);
        }

        storeRepository.save(store);
        basketRepository.save(basket);
    }

    @Override
    @Loggable
    @Notifiable
    public void buy() {
        basketRepository.deleteAll();
    }

}
