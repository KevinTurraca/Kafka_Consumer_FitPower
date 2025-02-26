package com.gym.fit_power.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrainerEventListener {

    @KafkaListener(topics="trainer-topic")
    public void handleTrainerEvent(String message) {
        log.info("Trainer created with CUIT: {}", message);
    }
}
