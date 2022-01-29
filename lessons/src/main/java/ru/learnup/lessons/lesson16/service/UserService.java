package ru.learnup.lessons.lesson16.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails getUserByLogin(String login);
}
