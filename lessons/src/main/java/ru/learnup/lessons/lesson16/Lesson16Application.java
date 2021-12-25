package ru.learnup.lessons.lesson16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.lessons.lesson16.model.Basket;
import ru.learnup.lessons.lesson16.model.Store;
import ru.learnup.lessons.lesson16.service.Shop;
import ru.learnup.lessons.lesson16.service.impl.ShopImp;

@SpringBootApplication
public class Lesson16Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lesson16Application.class, args);
		Shop shop = context.getBean(ShopImp.class);
		System.out.println(shop.getDescriptionByName("iron"));

		shop.addProductToBasket("kettle", 1);

		var baskets = shop.getBasket();

		for (Basket basket : baskets){
			System.out.println(basket);
		}

		var stories = shop.getCatalog();

		for (Store store : stories){
			System.out.println(store);
		}

		shop.buy();
	}
}
