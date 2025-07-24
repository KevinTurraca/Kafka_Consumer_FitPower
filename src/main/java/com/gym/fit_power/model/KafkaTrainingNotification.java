package com.gym.fit_power.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@Data
@Entity
@ToString
@Table(name = "training_notification")
public class KafkaTrainingNotification extends KafkaNotification<Optional<Trainer>> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
