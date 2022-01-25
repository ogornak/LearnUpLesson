package ru.learnup.lessons.lesson16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.learnup.lessons.lesson16.model.BasketEntity;
import ru.learnup.lessons.lesson16.model.StoreEntity;
import ru.learnup.lessons.lesson16.service.Shop;
import ru.learnup.lessons.lesson16.service.impl.ShopImpl;

@SpringBootApplication
public class Lesson16Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson16Application.class, args);
	}
}
