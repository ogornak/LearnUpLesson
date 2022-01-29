package ru.learnup.lessons.lesson16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.lessons.lesson16.model.BasketEntity;
import ru.learnup.lessons.lesson16.model.StoreEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<StoreEntity, Integer> {
    @Query(value = "SELECT p.description FROM ProductEntity p WHERE p.id = ?1")
    String findDescriptionById(int id);

    @Query(value = "FROM BasketEntity b")
    List<BasketEntity> findAllBasket();

    List<StoreEntity> findAll();

    @Query("FROM BasketEntity b WHERE b.productId = ?1")
    BasketEntity findBasketById(int productId);

    default void saveBasket(BasketEntity basketEntity){
        saveBasket(basketEntity.getProductId(), basketEntity.getCount());
    }

    StoreEntity findByProductEntityId(int productEntityId);

    StoreEntity findById(int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Market.Basket (ProductId, Count) SELECT ?1, ?2", nativeQuery = true)
    void saveBasket(int productId, Integer count);

    default void updateBasket(BasketEntity basketEntity){
        updateBasket(basketEntity.getProductId(), basketEntity.getCount());
    }

    @Modifying
    @Query(value = "UPDATE BasketEntity b SET b.count = ?2 WHERE b.productId = ?1")
    void updateBasket(int productId, Integer count);

    @Modifying
    @Transactional
    @Query(value = "DELETE BasketEntity")
    void deleteBasketAll();
}
