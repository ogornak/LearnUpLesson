package ru.learnup.lessons.lesson16.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.lessons.lesson16.annotation.Loggable;
import ru.learnup.lessons.lesson16.annotation.Notifiable;
import ru.learnup.lessons.lesson16.model.BasketEntity;
import ru.learnup.lessons.lesson16.model.StoreEntity;
import ru.learnup.lessons.lesson16.repository.MarketRepository;
import ru.learnup.lessons.lesson16.service.Shop;

import java.util.List;

@Service
public class ShopImpl implements Shop {
    private MarketRepository marketRepository;

    @Autowired
    public ShopImpl(MarketRepository marketRepository){
        this.marketRepository = marketRepository;
    }

    @Override
    @Loggable
    public List<StoreEntity> getCatalog(){
        return marketRepository.findAll();
    }

    @Override
    @Loggable
    public List<BasketEntity> getBasket(){
        return marketRepository.findAllBasket();
    }

    @Override
    public StoreEntity findById(int id) { return marketRepository.findById(id); }

    @Override
    @Loggable
    public String getDescriptionById(int productId) {
        return marketRepository.findDescriptionById(productId);
    }

    @Override
    @Loggable
    @Transactional(
            timeout = 5,
            rollbackFor = {RuntimeException.class}
    )
    public void addProductToBasket(int productId, int count) throws Exception {
        BasketEntity basketEntity = new BasketEntity(productId, count);
        StoreEntity storeEntity = marketRepository.findByProductEntityId(productId);
        if(storeEntity.getCount() < count){
            throw new Exception("Count error");
        }
        storeEntity.setCount(storeEntity.getCount() - count);

        editStoreAndBasket(storeEntity, basketEntity);
    }

    void editStoreAndBasket(StoreEntity storeEntity, BasketEntity basketEntity) {
        BasketEntity basketEntityByName = marketRepository.findBasketById(basketEntity.getProductId());

        if(basketEntityByName != null){
            basketEntity = basketEntityByName;
            basketEntity.setCount(basketEntity.getCount() + 1);
        }
        else {
            marketRepository.saveBasket(basketEntity);
        }

        marketRepository.save(storeEntity);
    }

    @Override
    @Loggable
    @Notifiable
    public void buy() {
        marketRepository.deleteBasketAll();
    }

    @Override
    public void updateCount(int productId, int count) {
        StoreEntity storeEntity = marketRepository.findByProductEntityId(productId);
        storeEntity.setCount(storeEntity.getCount() + 1);
        marketRepository.save(storeEntity);
    }

}
