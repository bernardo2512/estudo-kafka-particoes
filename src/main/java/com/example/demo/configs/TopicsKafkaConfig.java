package com.example.demo.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TopicsKafkaConfig {

    @Value("${queue.teste.request}")
    private String queueRequest;

    @Value("${spring.kafka.binder.replication-factor}")
    private String replicationFactor;

    @Value("${spring.kafka.topic.partition-default}")
    private int defaultPartitions;


    @Bean
    public NewTopic topicTeste() {
        return new NewTopic(queueRequest, defaultPartitions, Short.parseShort(replicationFactor));
    }
}
