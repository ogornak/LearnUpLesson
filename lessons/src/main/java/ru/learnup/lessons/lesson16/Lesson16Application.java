package ru.learnup.lessons.lesson16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.lessons.lesson16.model.Basket;
import ru.learnup.lessons.lesson16.model.Store;
import ru.learnup.lessons.lesson16.service.Shop;
import ru.learnup.lessons.lesson16.service.impl.ShopImpl;

@SpringBootApplication
public class Lesson16Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lesson16Application.class, args);
		Shop shop = context.getBean(ShopImpl.class);
	/*	System.out.println(shop.getDescriptionById(2));



		var stories = shop.getCatalog();
//
		for (Store store : stories){
			System.out.println(store);
		}
*/
		try {
			shop.addProductToBasket(2, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//var baskets = shop.getBasket();
//
		//for (Basket basket : baskets){
		//	System.out.println(basket);
		//}
		//shop.buy();
	}
}
