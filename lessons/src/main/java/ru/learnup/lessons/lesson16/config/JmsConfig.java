package ru.learnup.lessons.lesson16.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import ru.learnup.lessons.lesson16.jms.JmsConsumerService;
import ru.learnup.lessons.lesson16.jms.JmsProducerService;
import ru.learnup.lessons.lesson16.service.Shop;

@Configuration
public class JmsConfig {

    //@Bean
    //public JmsConsumerService jmsConsumerService(
    //        JmsTemplate jmsTemplate,
    //        @Value("${spring.activemq.object-queue-name}") String targetQueueName,
    //        Shop shop){
    //    return new JmsConsumerService(jmsTemplate, targetQueueName, shop);
    //}

    @Bean
    public JmsProducerService jmsProducerService(
            JmsTemplate jmsTemplate,
            @Value("${spring.activemq.object-queue-name}") String targetQueueName){
        return new JmsProducerService(jmsTemplate, targetQueueName);
    }
}
