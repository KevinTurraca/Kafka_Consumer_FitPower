package com.gym.fit_power.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public abstract class KafkaNotification<E> {

    @NotNull
    @NotBlank
    private LocalDateTime date;

    @NotNull
    @NotBlank
    private E sender;

    @NotNull
    @NotBlank
    private Client client;

    @NotNull
    @NotBlank
    private String reason;

    @NotNull
    @NotBlank
    private String message;
}