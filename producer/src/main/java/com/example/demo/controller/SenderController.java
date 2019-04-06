package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RabbitMQSender;

@RestController
public class SenderController {

    @Autowired
    private RabbitMQSender sender;
    
    
    @PostMapping(value="/producer")
    public String produce(@RequestBody String message) {
        sender.send(message);
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
