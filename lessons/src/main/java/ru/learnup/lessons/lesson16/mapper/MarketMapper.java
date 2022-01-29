package ru.learnup.lessons.lesson16.mapper;

import ru.learnup.lessons.lesson16.controller.dto.BasketDto;
import ru.learnup.lessons.lesson16.controller.dto.ProductDto;
import ru.learnup.lessons.lesson16.controller.dto.StoreDto;
import ru.learnup.lessons.lesson16.model.BasketEntity;
import ru.learnup.lessons.lesson16.model.ProductEntity;
import ru.learnup.lessons.lesson16.model.StoreEntity;

public interface MarketMapper {
    StoreDto toDtoStore(StoreEntity storeEntity);
    ProductDto toDtoProduct(ProductEntity store);
    BasketDto toDtoBasket(BasketEntity basketEntity);
}
