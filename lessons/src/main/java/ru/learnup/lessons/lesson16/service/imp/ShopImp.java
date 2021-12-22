package ru.learnup.lessons.lesson16.service.imp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.annotation.Loggable;
import ru.learnup.lessons.lesson16.annotation.Notifiable;
import ru.learnup.lessons.lesson16.service.Shop;
import ru.learnup.lessons.lesson16.model.Product;

import java.util.*;

@Service
@Scope("singleton")
public class ShopImp implements Shop {
    private Map<String, Product> products = new HashMap<String,Product>();
    private Map<String,Product> basket = new HashMap<String,Product>();

    public ShopImp(){
        products.put("kettle", new Product("kettle", "new kettle", 200, 10));
        products.put("iron", new Product("iron", "new iron", 300,5));
        products.put("vacuum", new Product("vacuum", "new vacuum", 700, 2));
        products.put("phone", new Product("phone", "new phone", 1499, 1));
    }

    @Override
    @Loggable
    public void getCatalog(){
        for(Product product : products.values()){
            System.out.println("Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Count: " + product.getCount());
        }
    }

    @Override
    @Loggable
    public void getBasket(){
        for(Product product : basket.values()){
            System.out.println("Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Count: " + product.getCount());
        }
    }

    @Override
    @Loggable
    public void getDescriptionByName(String name){
        System.out.println("Description: " + products.get(name).getDescription());
    }

    @Override
    @Loggable
    public void addProductToBasket(String name, int count) {
        try {
            Product productInBasket;
            var product = products.get(name);
            if (product.getCount() < count) {
                System.out.println("Ошибка!");
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
            System.out.println(e.getMessage());;
        }
    }

    @Override
    @Loggable
    @Notifiable
    public void buy() {
        basket.clear();
    }

}
