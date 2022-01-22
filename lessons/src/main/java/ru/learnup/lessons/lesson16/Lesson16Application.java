package ru.learnup.lessons.lesson16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.lessons.lesson16.service.Shop;
import ru.learnup.lessons.lesson16.service.impl.ShopImpl;

@SpringBootApplication
public class Lesson16Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lesson16Application.class, args);
		Shop shop = context.getBean(ShopImpl.class);
		//System.out.println(shop.getDescriptionByName("iron"));

		//shop.addProductToBasket("kettle", 1);

		//var baskets = shop.getBasket();

		//for (Basket basket : baskets){
		//	System.out.println(basket);
		//}

		//var stories = shop.getCatalog();
//
		//for (Store store : stories){
		//	System.out.println(store);
		//}

		try {
			shop.addProductToBasket("vacuum", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//shop.addProductToBasket("iron", 1);

		//shop.buy();
	}
}
