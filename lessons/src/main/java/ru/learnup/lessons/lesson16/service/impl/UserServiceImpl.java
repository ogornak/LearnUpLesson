package ru.learnup.lessons.lesson16.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.domain.User;
import ru.learnup.lessons.lesson16.model.UserEntity;
import ru.learnup.lessons.lesson16.repository.UserRepository;
import ru.learnup.lessons.lesson16.service.UserService;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails getUserByLogin(String login) {
        UserEntity user = userRepository.findUserEntityByLogin(login);
        return new User(Arrays.asList(new SimpleGrantedAuthority(user.getRole())), user.getPassword(), user.getLogin());
    }
}
