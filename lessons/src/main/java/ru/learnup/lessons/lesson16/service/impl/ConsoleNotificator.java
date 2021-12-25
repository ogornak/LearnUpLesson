package ru.learnup.lessons.lesson16.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.service.Notification;

@Aspect
@Service
@Scope("singleton")
@Profile("local")
public class ConsoleNotificator implements Notification {

    @Pointcut("@annotation(ru.learnup.lessons.lesson16.annotation.Notifiable)")
    public void sendMessage(){
    }

    @After("sendMessage()")
    public void notifyAfterMethod(JoinPoint point){
        System.out.println("ConsoleNotificator");
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
