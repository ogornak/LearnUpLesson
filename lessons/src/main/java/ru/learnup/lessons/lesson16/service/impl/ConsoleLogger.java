package ru.learnup.lessons.lesson16.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.service.Log;

@Aspect
@Service
public class ConsoleLogger implements Log {
    public ConsoleLogger(){
        System.out.println("Create Logger");
    }

    @Pointcut("@annotation(ru.learnup.lessons.lesson16.annotation.Loggable)")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint point){
        System.out.println("Before execution method " + point.getSignature().getName());

        for (Object arg : point.getArgs()) {
            System.out.println("Arg: " + arg);
        }
    }

    @Override
    public void log(Object obj) {
        System.out.println(obj.toString());
    }
}
