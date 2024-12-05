package com.gym.fit_power.notification.event;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public abstract class Event <E> {
    private String id;
    private LocalDateTime date;
    private EventType type;
    private E entity;
}