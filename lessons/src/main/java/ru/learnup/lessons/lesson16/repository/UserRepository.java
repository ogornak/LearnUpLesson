package ru.learnup.lessons.lesson16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.lessons.lesson16.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findUserEntityByLogin(String login);
}
