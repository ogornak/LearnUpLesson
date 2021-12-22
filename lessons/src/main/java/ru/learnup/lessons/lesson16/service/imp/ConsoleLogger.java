package ru.learnup.lessons.lesson16.service.imp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.lessons.lesson16.service.Log;

@Service
@Scope("singleton")
public class ConsoleLogger implements Log {
    public ConsoleLogger(){
        System.out.println("Create Logger");
    }
    @Override
    public void log(Object obj) {
        System.out.println(obj.toString());
    }
}
