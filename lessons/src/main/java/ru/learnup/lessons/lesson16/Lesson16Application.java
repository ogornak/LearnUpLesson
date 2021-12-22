package ru.learnup.lessons.lesson16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.lessons.lesson16.service.Shop;
import ru.learnup.lessons.lesson16.service.imp.ShopImp;

@SpringBootApplication
public class Lesson16Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lesson16Application.class, args);
		Shop shop = context.getBean(ShopImp.class);
		shop.getCatalog();
		shop.getDescriptionByName("vacuum");
		shop.addProductToBasket("vacuum", 2);
		shop.getBasket();
		shop.getCatalog();
		shop.buy();
	}
}
