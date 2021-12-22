package ru.learnup.lessons.lesson16.service.imp;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.service.Notification;

@Service
@Scope("singleton")
@Profile("local")
public class ConsoleNotificator implements Notification {
    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
