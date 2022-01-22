package ru.learnup.lessons.lesson16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.learnup.lessons.lesson16.model.Basket;
import ru.learnup.lessons.lesson16.model.Store;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Store, Integer> {
    @Query(value = "SELECT p.description FROM Product p WHERE p.id = ?1")
    String findDescriptionById(int id);

    @Query(value = "FROM Basket b")
    List<Basket> findAllBasket();

    @Query("FROM Store s JOIN s.product")
    List<Store> findAll();

    @Query("FROM Basket b WHERE b.productId = ?1")
    Basket findBasketById(int productId);

    default void saveBasket(Basket basket){
        saveBasket(basket.getProductId(), basket.getCount());
    }

    Store findByProductId(int productId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Market.Basket (ProductId, Count) SELECT ?1, ?2", nativeQuery = true)
    void saveBasket(int productId, Integer count);

    default void updateBasket(Basket basket){
        updateBasket(basket.getProductId(), basket.getCount());
    }

    @Modifying
    @Query(value = "UPDATE Basket b SET b.count = ?2 WHERE b.productId = ?1")
    void updateBasket(int productId, Integer count);

    @Modifying
    @Transactional
    @Query(value = "DELETE Basket")
    void deleteBasketAll();
}
