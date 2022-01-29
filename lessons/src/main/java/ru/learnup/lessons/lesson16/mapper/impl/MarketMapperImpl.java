package ru.learnup.lessons.lesson16.mapper.impl;

import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.controller.dto.BasketDto;
import ru.learnup.lessons.lesson16.controller.dto.ProductDto;
import ru.learnup.lessons.lesson16.controller.dto.StoreDto;
import ru.learnup.lessons.lesson16.mapper.MarketMapper;
import ru.learnup.lessons.lesson16.model.BasketEntity;
import ru.learnup.lessons.lesson16.model.ProductEntity;
import ru.learnup.lessons.lesson16.model.StoreEntity;

@Service
public class MarketMapperImpl implements MarketMapper {
    @Override
    public StoreDto toDtoStore(StoreEntity storeEntity) {
        return new StoreDto(storeEntity.getId(), storeEntity.getPrice(), storeEntity.getCount(), toDtoProduct(storeEntity.getProductEntity()));
    }

    @Override
    public ProductDto toDtoProduct(ProductEntity productEntity) {
        return new ProductDto(productEntity.getId(), productEntity.getName(), productEntity.getDescription());
    }

    @Override
    public BasketDto toDtoBasket(BasketEntity basketEntity) {
        return new BasketDto(basketEntity.getProductId(), basketEntity.getCount());
    }
}
