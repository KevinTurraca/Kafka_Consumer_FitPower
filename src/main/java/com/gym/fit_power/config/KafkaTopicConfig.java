package com.gym.fit_power.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic trainerEventsTopic(){
        return TopicBuilder.name("trainer-events")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic customerEventsTopic(){
        return TopicBuilder.name("customer-events")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
