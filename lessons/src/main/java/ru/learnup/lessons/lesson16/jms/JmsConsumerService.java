package ru.learnup.lessons.lesson16.jms;

import javax.jms.JMSException;
import javax.jms.Message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import ru.learnup.lessons.lesson16.domain.message.ProductMessageBody;
import ru.learnup.lessons.lesson16.service.Shop;

import javax.annotation.PostConstruct;
import javax.jms.ObjectMessage;

@Slf4j
public class JmsConsumerService {
    private JmsTemplate jmsTemplate;
    private String targetQueueName;
    private Shop shop;

    public JmsConsumerService(JmsTemplate jmsTemplate, String targetQueueName, Shop shop){
        this.jmsTemplate = jmsTemplate;
        this.targetQueueName = targetQueueName;
        this.shop = shop;
    }

    @PostConstruct
    public void consumerProcess(){
        new Thread(() -> {
            while (true){
                final Message message = jmsTemplate.receive(targetQueueName);
                if(message instanceof ObjectMessage){
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        final ProductMessageBody msg = (ProductMessageBody)objectMessage.getObject();
                        log.info("В магазин пришло: " + msg.toString());
                        shop.updateCount(msg.getProductId(), msg.getCount());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Unknown message!");
                }
            }
        }).start();
    }
}
