package ru.learnup.lessons.lesson16.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.service.Log;
import ru.learnup.lessons.lesson16.service.Notification;
import ru.learnup.lessons.lesson16.service.Shop;
import ru.learnup.lessons.lesson16.model.Product;

import java.util.*;

@Service
@Scope("singleton")
public class ShopImp implements Shop {
    private Map<String, Product> products = new HashMap<String,Product>();
    private Map<String,Product> basket = new HashMap<String,Product>();

    @Autowired
    private Log logger;

    @Autowired
    private Notification notificator;

    public ShopImp(){
        products.put("kettle", new Product("kettle", "new kettle", 200, 10));
        products.put("iron", new Product("iron", "new iron", 300,5));
        products.put("vacuum", new Product("vacuum", "new vacuum", 700, 2));
        products.put("phone", new Product("phone", "new phone", 1499, 1));
    }

    @Override
    public void getCatalog(){
        logger.log("Get catalog");
        for(Product product : products.values()){
            System.out.println("Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Count: " + product.getCount());
        }
    }

    @Override
    public void getBasket(){
        logger.log("Get basket");
        for(Product product : basket.values()){
            System.out.println("Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Count: " + product.getCount());
        }
    }

    @Override
    public void getDescriptionByName(String name){
        logger.log("Get description");
        System.out.println("Description: " + products.get(name).getDescription());
    }

    @Override
    public void addProductToBasket(String name, int count) {
        logger.log("Add " + name + " to basket");
        try {
            Product productInBasket;
            var product = products.get(name);
            if (product.getCount() < count) {
                System.out.println("Ошибка!");
                logger.log("Error");
                return;
            }
            product.setCount(product.getCount() - count);

            productInBasket = basket.get(name);
            if (productInBasket == null) {
                productInBasket = (Product) product.clone();
                productInBasket.setCount(0);
            }
            productInBasket.setCount(productInBasket.getCount() + count);

            basket.put(name, productInBasket);
        }
        catch (Exception e) {
            logger.log(e.getMessage());
            System.out.println(e.getMessage());;
        }
    }

    @Override
    public void buy() {
        basket.clear();
        logger.log("Buy");
        notificator.notify("Buy");
    }

}
