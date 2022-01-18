package com.emailmanager.emailmanagerspringbootkafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

public class KafkaConsumer {
    //@KafkaListener(topics = "email_manager_topic")
    public void processMessage(String user) {
        System.out.println("Message received by consumer 1: " + user.toString());
    }
}
