package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.configs.KafkaRequestSender;

@Service
public class SendToQueueService {
    
    @Value("${queue.teste.request}")
    private String fila;

    @Autowired
    private KafkaRequestSender kafkaRequestSender;
    
    public void execute(Map<String,Object> mapTeste){
        this.kafkaRequestSender.send(fila, mapTeste);
    }

}
