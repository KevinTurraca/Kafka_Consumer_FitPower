package com.gym.fit_power.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "nutrition_notifications")
public class KafkaNutritionNotification extends KafkaNotification<Nutritionist> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}