package com.example.demo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TesteListener {
    
    @KafkaListener(topics = "${queue.teste.request}")
    public void execute(String topicObjectReply){
        System.out.println(topicObjectReply);
    }
}
