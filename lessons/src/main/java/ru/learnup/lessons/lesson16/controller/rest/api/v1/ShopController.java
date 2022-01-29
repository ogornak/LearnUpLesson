package ru.learnup.lessons.lesson16.controller.rest.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.learnup.lessons.lesson16.controller.dto.BasketDto;
import ru.learnup.lessons.lesson16.controller.dto.StoreDto;
import ru.learnup.lessons.lesson16.mapper.MarketMapper;
import ru.learnup.lessons.lesson16.model.BasketEntity;
import ru.learnup.lessons.lesson16.model.StoreEntity;
import ru.learnup.lessons.lesson16.service.Shop;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {
    private Shop shop;
    private MarketMapper marketMapper;

    @Autowired
    public ShopController(Shop shop, MarketMapper marketMapper){
        this.shop = shop;
        this.marketMapper = marketMapper;
    }

    @GetMapping
    public List<StoreDto> getAll(){
        final Collection<StoreEntity> storeEntities = shop.getCatalog();
        return storeEntities.stream()
                .map(x -> marketMapper.toDtoStore(x))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StoreDto getOne(@PathVariable Integer id){
        return marketMapper.toDtoStore(shop.findById(id));
    }

    @GetMapping("/basket")
    public List<BasketDto> getBasket(){
        final Collection<BasketEntity> baskets = shop.getBasket();
        return baskets.stream()
                .map(x -> marketMapper.toDtoBasket(x))
                .collect(Collectors.toList());
    }

    @PostMapping("/basket")
    public String addToBasket(@RequestBody StoreDto storeDto) throws Exception {
        shop.addProductToBasket(storeDto.getProduct().getId(), 1);
        return storeDto.getProduct().getName() + " добавлен в корзину";
    }

}
