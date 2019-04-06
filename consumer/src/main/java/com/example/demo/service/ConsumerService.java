package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.config.SpringAmqpConfig;

@Service
public class ConsumerService {

    @RabbitListener(queues=SpringAmqpConfig.queueName)
    public void consume(String message) {
        System.out.println("Message received:"+message);
    }
}
