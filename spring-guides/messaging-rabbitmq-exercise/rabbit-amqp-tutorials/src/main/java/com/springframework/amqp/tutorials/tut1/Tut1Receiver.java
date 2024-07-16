package com.springframework.amqp.tutorials.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

public class Tut1Receiver {
    
    @RabbitHandler
    public void receiver(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
