package ru.learnup.lessons.lesson16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.lessons.lesson16.model.Basket;
import ru.learnup.lessons.lesson16.model.Store;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Store, String> {
    @Query(value = "SELECT p.Description FROM Market.Product p WHERE p.Name = ?1", nativeQuery = true)
    String findDescriptionByName(String name);

    @Query(value = "FROM Basket b")
    List<Basket> findAllBasket();

    @Query("FROM Store s JOIN s.product")
    List<Store> findAll();

    @Query("FROM Basket b WHERE b.name = ?1")
    Basket findBasketByName(String name);

    default void saveBasket(Basket basket){
        saveBasket(basket.getName(), basket.getCount());
    }

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Market.Basket (Name, Count) SELECT ?1, ?2", nativeQuery = true)
    void saveBasket(String name, Integer count);

    default void updateBasket(Basket basket){
        updateBasket(basket.getName(), basket.getCount());
    }

    @Modifying
    @Transactional
    @Query(value = "UPDATE Market.Basket SET Count = ?2 WHERE Name = ?1", nativeQuery = true)
    void updateBasket(String name, Integer count);

    @Modifying
    @Transactional
    @Query(value = "DELETE Market.Basket", nativeQuery = true)
    void deleteBasketAll();
}
