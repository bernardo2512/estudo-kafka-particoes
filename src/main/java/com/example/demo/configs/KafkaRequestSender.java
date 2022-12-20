package com.example.demo.configs;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class KafkaRequestSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void send(String topicRequest, Object requestObject)  {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicRequest,
                new Gson().toJson(requestObject));
        record.headers().add(new RecordHeader(KafkaHeaders.TOPIC, topicRequest.getBytes()));
        kafkaTemplate.send(record);
    }
}

