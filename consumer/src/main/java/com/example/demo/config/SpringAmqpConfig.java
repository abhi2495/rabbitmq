package com.example.demo.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.ConsumerService;

@Configuration
public class SpringAmqpConfig {

    public final static String queueName = "myQ";
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        /**
         * For localhost connection, first we need to change the virtual host from admin UI
         */
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setVirtualHost("/");
        factory.setPassword("guest");
        /**
         * Cloud AMQP provides free hosted RabbitMQ connection. To connect to that use the following config:
         */
        /*
         * factory.setHost("mustang.rmq.cloudamqp.com");
         * factory.setUsername("yezraebu");
         * factory.setVirtualHost("yezraebu");
         * factory.setPassword("uK2TDf4P2RZqlMKAQxvtSDtAOYfM_whz");
         */
      
        return factory;
    }
    
    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
    
    @Bean
    SimpleMessageListenerContainer springAmqpContainer(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ConsumerService messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, "consume");
    }
    
    
}
