package ru.learnup.lessons.lesson16.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.learnup.lessons.lesson16.domain.message.ProductMessageBody;
import ru.learnup.lessons.lesson16.service.Shop;

@Slf4j
@Component
public class MessageListener {

    private Shop shop;

    @Autowired
    public MessageListener(Shop shop){
        this.shop = shop;
    }

    @JmsListener(destination = "productsQueue")
    public void getObjectMessage(ProductMessageBody msg) {
        log.info("В магазин пришло: " + msg.toString());
        shop.updateCount(msg.getProductId(), msg.getCount());
    }
}
