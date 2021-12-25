package ru.learnup.lessons.lesson16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.lessons.lesson16.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
}
