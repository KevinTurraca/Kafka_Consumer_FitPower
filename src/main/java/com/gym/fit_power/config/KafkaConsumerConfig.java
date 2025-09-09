package com.gym.fit_power.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@Slf4j
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        // ¡AQUÍ ESTÁ LA NUEVA LÓGICA DE MANEJO DE ERRORES!
        // Este manejador ahora recibirá los errores de deserialización.
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((record, exception) -> {
            // Log con más detalle, incluyendo la causa raíz de la excepción
            Throwable cause = exception.getCause() != null ? exception.getCause() : exception;
            log.error("❌ Error al procesar mensaje. Mensaje descartado. Topic: {}, Partition: {}, Offset: {}. Causa: {}",
                    record.topic(), record.partition(), record.offset(), cause.getMessage());
        }, new FixedBackOff(1000L, 2L)); // Reintentar 2 veces con 1 segundo de espera

        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}