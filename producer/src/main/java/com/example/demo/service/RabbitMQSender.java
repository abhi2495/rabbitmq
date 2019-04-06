package com.example.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.SpringAmqpConfig;

@Service
public class RabbitMQSender {
    
    @Autowired
    private RabbitTemplate rtemplate;
    
    public void send(String msg) {
        rtemplate.convertAndSend(SpringAmqpConfig.exchangeName, SpringAmqpConfig.routingKey, msg);
    }

}
