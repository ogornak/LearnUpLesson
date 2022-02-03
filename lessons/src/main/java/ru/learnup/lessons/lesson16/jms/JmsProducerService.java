package ru.learnup.lessons.lesson16.jms;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.core.JmsTemplate;
import ru.learnup.lessons.lesson16.domain.message.ProductMessageBody;

import javax.annotation.PostConstruct;
import javax.jms.ObjectMessage;

public class JmsProducerService {
    private JmsTemplate jmsTemplate;
    private String targetQueueName;

    public JmsProducerService(JmsTemplate jmsTemplate, String targetQueueName){
        this.jmsTemplate = jmsTemplate;
        this.targetQueueName = targetQueueName;
    }

    @PostConstruct
    public void publishProcess(){
        new Thread(() -> {
            while (true){
                try{
                    Thread.sleep(5000);
                    jmsTemplate.send(targetQueueName, session -> {
                        final ObjectMessage msg = new ActiveMQObjectMessage();
                        msg.setObject(new ProductMessageBody(2, 1));
                        return msg;
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
