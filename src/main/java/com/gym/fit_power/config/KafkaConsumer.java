package com.gym.fit_power.config;

import com.gym.fit_power.model.KafkaNotification;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import java.util.Map;
import java.util.HashMap;

@EnableKafka
@Configuration
public class KafkaConsumer {

    @Bean
    public ConsumerFactory<String, KafkaNotification<?>> consumerFactory() {
        Map<String, String> props = new HashMap<>();
        String bootstrapAddress = "localhost:9092";
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(JsonSerializer.TYPE_MAPPINGS, "com.gym.fit_power:com.gym.fit_power.model.KafkaNotification");
        final JsonDeserializer<KafkaNotification<?>> deserializer = new JsonDeserializer<>();
        return new DefaultKafkaConsumerFactory(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaNotification<?>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaNotification<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}