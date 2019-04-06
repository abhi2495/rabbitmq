package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAmqpConfig {
    
    public final static String queueName = "myQ";
    public final static String exchangeName = "myXchange";
    public final static String routingKey = "myRoutingKey";
    
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
    public Queue queue() {
        return new Queue(queueName, false);// boolean durable - if we are declaring a durable queue, the queue will survive a server restart
    }
    
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }
    
    @Bean
    public Binding binding(Queue queue,DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
    
    /*
    @Bean
    public MessageConverter jsonMessageConverter() {
            return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
            final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(jsonMessageConverter());
            return rabbitTemplate;
    }
     */
    

}
